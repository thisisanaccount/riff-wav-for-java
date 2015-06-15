package test;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import com.skratchdot.riff.wav.Chunk;
import com.skratchdot.riff.wav.ChunkDataList;
import com.skratchdot.riff.wav.ChunkDataListType;
import com.skratchdot.riff.wav.ChunkDataListTypeID;
import com.skratchdot.riff.wav.ChunkDataListTypeIcop;
import com.skratchdot.riff.wav.ChunkTypeID;
import com.skratchdot.riff.wav.ChunkUnknown;
import com.skratchdot.riff.wav.ParseChunkException;
import com.skratchdot.riff.wav.RIFFWave;
import com.skratchdot.riff.wav.WavFactory;
import com.skratchdot.riff.wav.impl.ChunkDataListTypeIcopImpl;
import com.skratchdot.riff.wav.impl.ChunkDataListUpperCaseImpl;
import com.skratchdot.riff.wav.util.RiffWaveException;

public class RiffWavTest {
	private String l = "============================================";
	private static final String MY_INFO = "I 改变 it!";

	@Test
	public void testParseChunk1() {
		String pathname = Test.class.getResource("/1.wav").getFile();
		File file = new File(pathname);
		try {
			RIFFWave riffWave = WavFactory.eINSTANCE.createRIFFWave(file);
			printRiffWave(riffWave);
			printParseChunkExceptions(riffWave);
			File file2 = new File("target/test-classes/1_.wav");
			riffWave.write(file2);
			addMyInfo(riffWave, MY_INFO, "target/test-classes/1_1.wav");
			System.out.println(l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void addMyInfo(RIFFWave riffWave, String myInfo, String filePathName) throws IOException, RiffWaveException {
		EList<Chunk> chunks = riffWave.getChunks();
		ChunkDataList chunkDataList = null;
		for (Chunk chunk : chunks) {
			if (chunk instanceof ChunkDataList) {
				chunkDataList = (ChunkDataList) chunk;
				break;
			}
		}
		if (chunkDataList == null) {
			chunkDataList = new ChunkDataListUpperCaseImpl();
			chunkDataList.setTypeID(ChunkDataListTypeID.INFO);
			chunks.add(chunkDataList);
		}
		EList<ChunkDataListType> dataListChunks = chunkDataList.getDataListChunks();
		ChunkDataListTypeIcop chunkDataListTypeIcop = null;
		for (ChunkDataListType chunkDataListType : dataListChunks) {
			if (chunkDataListType instanceof ChunkDataListTypeIcop) {
				chunkDataListTypeIcop = (ChunkDataListTypeIcop) chunkDataListType;
				break;
			}
		}
		if (chunkDataListTypeIcop == null) {
			chunkDataListTypeIcop = new ChunkDataListTypeIcopImpl();
			dataListChunks.add(chunkDataListTypeIcop);
		}
		String textAsString = chunkDataListTypeIcop.getTextAsString();
		System.out.println("src text : [" + textAsString + "]");
		chunkDataListTypeIcop.setText(myInfo.getBytes());
		File file3 = new File(filePathName);
		riffWave.write(file3);
	}

	private void printParseChunkExceptions(RIFFWave riffWave) {
		EList<ParseChunkException> parseChunkExceptions = riffWave.getParseChunkExceptions();
		int size = parseChunkExceptions.size();
		if (size > 0) {
			System.err.println("parseChunkExceptions size : " + size);
			for (ParseChunkException parseChunkException : parseChunkExceptions) {
				System.err.println(parseChunkException);
			}
		}
	}

	private void printRiffWave(RIFFWave riffWave) {
		EList<Chunk> chunks = riffWave.getChunks();
		for (Chunk chunk : chunks) {
			printChunks(chunk);
		}
	}

	@Test
	public void testParseChunk2() {
		String pathname = Test.class.getResource("/2.wav").getFile();
		File file = new File(pathname);
		try {
			RIFFWave riffWave = WavFactory.eINSTANCE.createRIFFWave(file);
			printRiffWave(riffWave);
			printParseChunkExceptions(riffWave);
			File file2 = new File("target/test-classes/2_.wav");
			riffWave.write(file2);
			addMyInfo(riffWave, MY_INFO, "target/test-classes/2_2.wav");
			System.out.println(l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void printChunks(Chunk chunk) {
		ChunkTypeID chunkTypeID = chunk.getChunkTypeID();
		System.out.println("chunkTypeID : [" + chunkTypeID + "]");
		if (chunk instanceof ChunkUnknown) {
			ChunkUnknown chunkUnknown = (ChunkUnknown) chunk;
			Long value = chunkUnknown.getUnknownChunkTypeIdValue();
			System.out.println("Unknown : [" + value + "]");
		}
		if (chunk instanceof ChunkDataListType) {
			ChunkDataListType chunkDataListType = (ChunkDataListType) chunk;
			String textAsString = chunkDataListType.getTextAsString();
			System.out.println("textAsString : [" + textAsString + "]");
		}
		if (chunk instanceof ChunkDataList) {
			ChunkDataList chunkDataList = (ChunkDataList) chunk;
			ChunkDataListTypeID typeID = chunkDataList.getTypeID();
			System.out.println("typeID : [" + typeID + "]");
			EList<ChunkDataListType> chunkDataListTypes = chunkDataList.getDataListChunks();
			for (ChunkDataListType chunkDataListType : chunkDataListTypes) {
				printChunks(chunkDataListType);
			}
		}
	}
}
