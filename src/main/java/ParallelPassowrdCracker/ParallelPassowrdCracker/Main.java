package ParallelPassowrdCracker.ParallelPassowrdCracker;

public class Main {
    public static void main(String[] args) {
        try {
            String[] hashes = {
            	// alex
                "4135aa9dc1b842a653dea846903ddb95bfb8c5a10c504a7fa16e10bc31d1fdf0",
                // diana
                "1b2fc9341a16ae4e30082965d537ae47c21a0f27fd43eab78330ed81751ae6db",
                // bogdan
                "a023cc1cd2e93015718c625b01a81d5b85da7c2654c7d7572a0d02fcec8e59c9"};
            PasswordCracker pbf = new PasswordCracker(6, hashes);
            pbf.FindPasswords();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
