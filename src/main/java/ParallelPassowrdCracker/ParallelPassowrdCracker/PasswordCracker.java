package ParallelPassowrdCracker.ParallelPassowrdCracker;

import jakarta.xml.bind.DatatypeConverter;
import java.util.*;
import java.util.concurrent.*;

public class PasswordCracker {
    private int maxLength;
    private String[] hashes;
    private byte[][] digests;

    public PasswordCracker(int maxLength, String[] hashes) {
        this.maxLength = maxLength;
        this.hashes = hashes;
        digests = new byte[hashes.length][];
        for (int i = 0; i < hashes.length; ++i)
            digests[i] = DatatypeConverter.parseHexBinary(hashes[i]);
    }

    public void FindPasswords() throws Exception {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService svc = Executors.newFixedThreadPool(processors);
        List<Future<?>> tasks = new ArrayList<>();
        
        for (int len = 1; len <= maxLength; len++) {
            for (int i = 0; i < 26; ++i)
                tasks.add(svc.submit(new PasswordFinderTask((byte)(97 + i), len, hashes, digests)));
        }
        
        for (Future<?> task : tasks)
            task.get();
        
        svc.shutdown();
    }

    public static boolean NextPassword(byte[] passwd, int start) {
        int len = passwd.length;
        for (int i = len - 1; i >= start; --i) {
            if (passwd[i] < 122) {
                ++passwd[i];
                return true;
            }
            passwd[i] = 97;
        }
        return false;
    }
}
