package com.skratchdot.riff.wav.impl;

import java.nio.ByteOrder;

import com.skratchdot.riff.wav.ChunkAfsp;
import com.skratchdot.riff.wav.ChunkTypeID;
import com.skratchdot.riff.wav.RIFFWave;
import com.skratchdot.riff.wav.util.ExtendedByteBuffer;
import com.skratchdot.riff.wav.util.RiffWaveException;

public class ChunkAfspImpl extends ChunkImpl implements ChunkAfsp {

	private byte[] data;

	@Override
	public ChunkTypeID getChunkTypeID() {
		return ChunkTypeID.AFSP;
	}

	@Override
	public int getChunkTypeIDValue() {
		return ChunkTypeID.AFSP_VALUE;
	}

	@Override
	public long getSize() {
		return data == null ? 0 : data.length;
	}

	@Override
	public void init(RIFFWave riffWave, ExtendedByteBuffer buf) throws RiffWaveException {
		// Check Chunk Type ID
		if (ChunkTypeID.get((int) buf.getUnsignedInt()) != this.getChunkTypeID())
			throw new RiffWaveException("Invalid Chunk ID for " + this.getChunkTypeID().getLiteral());

		// Read in data size
		int chunkSize = (int) buf.getUnsignedInt();

		// Read in original sample data
		byte[] data = new byte[chunkSize];
		buf.getBytes(data);
		this.setData(data);
	}

	@Override
	public byte[] toByteArray() throws RiffWaveException {
		ExtendedByteBuffer buf = new ExtendedByteBuffer((int) this.getSize() + 8);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		buf.putUnsignedInt(this.getChunkTypeIDValue());
		buf.putUnsignedInt(this.getSize());
		if (this.getData() != null) {
			buf.putBytes(this.getData());
		}

		return buf.array();
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
