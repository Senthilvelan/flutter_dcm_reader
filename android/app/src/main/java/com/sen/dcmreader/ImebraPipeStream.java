package com.sen.dcmreader;

import com.imebra.*;

import java.io.IOException;
import java.io.InputStream;


public class ImebraPipeStream implements Runnable {

    private PipeStream mImebraPipe;
    private InputStream mStream;

    public ImebraPipeStream(com.imebra.PipeStream pipe, InputStream stream) {
        mImebraPipe = pipe;
        mStream = stream;
    }

    @Override
    public void run() {
        StreamWriter pipeWriter = new StreamWriter(mImebraPipe.getStreamOutput());
        try {
            byte[] buffer = new byte[128000];
            MutableMemory memory = new MutableMemory();


            for (int readBytes = mStream.read(buffer); readBytes >= 0; readBytes = mStream.read(buffer)) {
                if (readBytes > 0) {
                    memory.assign(buffer);
                    memory.resize(readBytes);
                    pipeWriter.write(memory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pipeWriter.delete();
            mImebraPipe.close(50000);
        }
    }
}

