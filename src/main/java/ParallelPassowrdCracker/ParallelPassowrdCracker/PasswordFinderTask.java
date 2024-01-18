package ParallelPassowrdCracker.ParallelPassowrdCracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordFinderTask implements Runnable {
    private byte ch;
    private MessageDigest md = MessageDigest.getInstance("SHA-256");
    private int length;
    private String[] hashes;
    private byte[][] digests;

    public PasswordFinderTask(byte ch, int length, String[] hashes, byte[][] digests) throws NoSuchAlgorithmException {
        this.ch = ch;
        this.length = length;
        this.hashes = hashes;
        this.digests = digests;
    }

    public void run() {
        byte[] passwd = new byte[length];
        Arrays.fill(passwd, (byte)97);
        passwd[0] = ch;
        while (length > 0) {
            byte[] digest = md.digest(passwd);
            for (int i = 0; i < hashes.length; ++i) {
                if (Arrays.equals(digest, digests[i])) {
                    length--;
                    System.out.println("Password -> " + new String(passwd) + " for hash -> " + hashes[i]);
                    break;
                }
            }
            if (!PasswordCracker.NextPassword(passwd, 1))
                break;
        }
    }
}
