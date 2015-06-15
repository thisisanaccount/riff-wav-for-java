package com.skratchdot.riff.wav.impl;

import com.skratchdot.riff.wav.ChunkDataListTypeIsft;
import com.skratchdot.riff.wav.ChunkTypeID;

public class ChunkDataListTypeIsftImpl extends ChunkDataListInfoTypeImpl implements ChunkDataListTypeIsft {

	@Override
	public ChunkTypeID getChunkTypeID() {
		return ChunkTypeID.ISFT;
	}

	@Override
	public int getChunkTypeIDValue() {
		return ChunkTypeID.ISFT_VALUE;
	}

}
