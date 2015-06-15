package com.skratchdot.riff.wav.impl;

import com.skratchdot.riff.wav.ChunkDataListTypeIcmt;
import com.skratchdot.riff.wav.ChunkTypeID;

public class ChunkDataListTypeIcmtImpl extends ChunkDataListInfoTypeImpl implements ChunkDataListTypeIcmt {

	@Override
	public ChunkTypeID getChunkTypeID() {
		return ChunkTypeID.ICMT;
	}

	@Override
	public int getChunkTypeIDValue() {
		return ChunkTypeID.ICMT_VALUE;
	}

}
