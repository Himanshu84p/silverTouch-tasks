import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prac60_BlockchainExample {
    private List<Block> chain;
    private static final Logger logger = Logger.getLogger(Prac60_BlockchainExample.class.getName());

    public Prac60_BlockchainExample() {
        this.chain = new ArrayList<>();
        // Genesis block
        addBlock(new Block("Genesis Block", "0"));
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        newBlock.mineBlock(4); // mineBlock with a prefix of 4 zeros
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateBlockHash())) {
                logger.log(Level.SEVERE, "Block #" + currentBlock.getIndex() + " hash is not valid.");
                return false;
            }

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                logger.log(Level.SEVERE, "Blockchain is tampered at Block #" + currentBlock.getIndex());
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Create a new blockchain
        Prac60_BlockchainExample blockchain = new Prac60_BlockchainExample();

        // Add Block #1
        blockchain.addBlock(new Block("Transaction 1", blockchain.getLatestBlock().getHash()));

        // Add Block #2
        blockchain.addBlock(new Block("Transaction 2", blockchain.getLatestBlock().getHash()));

        // Check and print if the blockchain is valid after adding Block #1 and Block #2
        System.out.println("Blockchain is valid: " + blockchain.isChainValid());

        // Attempt to add a tampered Block #3
        blockchain.addBlock(new Block("Tampered Transaction", blockchain.getLatestBlock().getHash()));

        // Check and print if the blockchain is valid after attempting to add the tampered Block #3
        System.out.println("Blockchain is valid: " + blockchain.isChainValid());
    }
}

class Block {
    private static final Logger logger = Logger.getLogger(Block.class.getName());
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private int index;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateBlockHash();
    }

    public String calculateBlockHash() {
        String dataToHash = previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + data
                +index;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(dataToHash.getBytes("UTF-8"));
            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                buffer.append(String.format("%02x", b));
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            return null;
        }
    }

    public void mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
