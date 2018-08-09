import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;

public class T {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = null;
        RandomAccessFile bFile = null;
        ByteBuffer buffer1 = ByteBuffer.allocate(0);
        ByteBuffer buffer2 = ByteBuffer.allocate(0);
        try {
            aFile = new RandomAccessFile("/home/grzegorz/Pobrane/s14.wav", "r");
            bFile = new RandomAccessFile("/home/grzegorz/Pobrane/s13.wav", "r");

            FileChannel inChannel1 = aFile.getChannel();
            FileChannel inChannel2 = bFile.getChannel();
            buffer1 = ByteBuffer.allocate(200000);
            buffer2 = ByteBuffer.allocate(200000);
            /*while(*/
            System.out.println(inChannel1.read(buffer1)/* > 0)*/);
            System.out.println(/*while(*/inChannel2.read(buffer2)/* > 0)*/);
        /*{
            buffer.flip();
            for (int i = 0; i < buffer.limit(); i++)
            {
                System.out.print((char) buffer.get());
            }
            buffer.clear(); // do something with the data and clear/compact it.
        }*/
            inChannel1.close();
            inChannel2.close();
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            vorbis = IOUtil.ioResourceToByteBuffer(resource, bufferSize);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

       /* IntBuffer error   = BufferUtils.createIntBuffer(1);
        long      decoder = stb_vorbis_open_memory(vorbis, error, null);
        if (decoder == NULL) {
            throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
        }

        stb_vorbis_get_info(decoder, info);

        int channels = info.channels();

        ShortBuffer pcm = BufferUtils.createShortBuffer(stb_vorbis_stream_length_in_samples(decoder) * channels);

        stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm);
        stb_vorbis_close(decoder);*/
        buffer1.flip();
        buffer2.flip();
        ShortBuffer pcm = ShortBuffer.allocate(100000);
        for(int i =0 ; i < 100000;i++){
            int aShort = buffer1.getShort() + Short.MAX_VALUE;
            int bShort = buffer2.getShort() + Short.MAX_VALUE;
            int res = (aShort + bShort)/2 - Short.MAX_VALUE;
            System.out.println(aShort + " " + bShort + " " + (short)((aShort + aShort)/2)+" " + (short)res);
            pcm.put((short)((res)));
        }
        pcm.flip();
        FileOutputStream out = new FileOutputStream("the-file-name");
        ByteBuffer bb = ByteBuffer.allocate(400000);
        for(int i =0 ; i < 100000;i++){
            short i1 = pcm.get();
            bb.putShort(i1);
        }
        bb.flip();
        out.write(bb.array());
        out.close();
    }
}
