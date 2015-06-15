package com.skratchdot.riff.wav.impl;

import java.nio.ByteOrder;

import com.skratchdot.riff.wav.ChunkTypeID;
import com.skratchdot.riff.wav.RIFFWave;
import com.skratchdot.riff.wav.util.ExtendedByteBuffer;
import com.skratchdot.riff.wav.util.RiffWaveException;

public class ChunkDataListInfoTypeImpl extends ChunkDataListTypeImpl {
	@Override
	public void init(RIFFWave riffWave, ExtendedByteBuffer buf) throws RiffWaveException {
		// Check Chunk Type ID
		if (ChunkTypeID.get((int) buf.getUnsignedInt()) != this.getChunkTypeID())
			throw new RiffWaveException("Invalid Chunk ID for " + this.getChunkTypeID().getLiteral());

		// Read in data size
		int chunkSize = (int) buf.getUnsignedInt();

		byte[] newText = new byte[chunkSize];
		buf.getBytes(newText);
		this.setText(newText);
	}
	@Override
	public long getSize() {
		return this.getText() == null ? 0 : this.getText().length;
	}

	@Override
	public byte[] toByteArray() throws RiffWaveException {
		ExtendedByteBuffer buf = new ExtendedByteBuffer((int) this.getSize() + 8);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		buf.putUnsignedInt(this.getChunkTypeIDValue());
		buf.putUnsignedInt(this.getSize());
//		buf.putUnsignedInt(this.getCuePointID());
		if (this.getText() != null) {
			buf.putBytes(this.getText());
		}

		return buf.array();
	}
}
