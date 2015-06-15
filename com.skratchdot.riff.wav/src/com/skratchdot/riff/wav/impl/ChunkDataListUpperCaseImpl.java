package com.skratchdot.riff.wav.impl;

import com.skratchdot.riff.wav.ChunkTypeID;

public class ChunkDataListUpperCaseImpl extends ChunkDataListImpl {
	@Override
	public ChunkTypeID getChunkTypeID() {
		return ChunkTypeID.LIST_UPPERCASE;
	}
	
	@Override
	public int getChunkTypeIDValue() {
		return ChunkTypeID.LIST_UPPERCASE_VALUE;
	}
}