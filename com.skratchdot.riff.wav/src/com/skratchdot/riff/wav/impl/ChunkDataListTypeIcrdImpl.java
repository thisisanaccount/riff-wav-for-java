package com.skratchdot.riff.wav.impl;

import com.skratchdot.riff.wav.ChunkDataListTypeIcrd;
import com.skratchdot.riff.wav.ChunkTypeID;

public class ChunkDataListTypeIcrdImpl extends ChunkDataListInfoTypeImpl implements ChunkDataListTypeIcrd {
	
	
	@Override
	public ChunkTypeID getChunkTypeID() {
		return ChunkTypeID.ICRD;
	}

	@Override
	public int getChunkTypeIDValue() {
		return ChunkTypeID.ICRD_VALUE;
	}

	
}
