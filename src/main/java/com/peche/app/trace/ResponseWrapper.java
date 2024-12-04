package com.peche.app.trace;

import lombok.Getter;
import org.apache.commons.io.output.TeeOutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream bo = new ByteArrayOutputStream();
    @Getter
    private final long id;
    public ResponseWrapper(long requestId,HttpServletResponse response) {
        super(response);
        this.id = requestId;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        return new ServletOutputStream() {
            private final TeeOutputStream tee = new TeeOutputStream(ResponseWrapper.super.getOutputStream(), bo);

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {
                tee.write(b);
            }
        };
    }

    public byte[] toByteArray() {
        return bo.toByteArray();
    }

}
