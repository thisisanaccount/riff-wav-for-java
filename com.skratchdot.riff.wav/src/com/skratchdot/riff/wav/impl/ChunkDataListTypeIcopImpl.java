package com.skratchdot.riff.wav.impl;

import com.skratchdot.riff.wav.ChunkDataListTypeIcop;
import com.skratchdot.riff.wav.ChunkTypeID;

public class ChunkDataListTypeIcopImpl extends ChunkDataListInfoTypeImpl implements ChunkDataListTypeIcop {
	@Override
	public ChunkTypeID getChunkTypeID() {
		return ChunkTypeID.ICOP;
	}

	@Override
	public int getChunkTypeIDValue() {
		return ChunkTypeID.ICOP_VALUE;
	}
}
