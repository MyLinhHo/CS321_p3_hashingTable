import java.lang.reflect.Array;
/**
 * Hash Table class includes array of Hash Object
 *  with two main method is insert ans searching
 * author My-Linh Ho
 */
public abstract class Hashtable {
    protected HashObject[] hashTable;
    protected int size;
    private int numElement;
    private int totalFreq;
    private int totalProbe;

    public Hashtable(int size){
        hashTable = new HashObject[size];
        this.size = size;
        numElement = 0;
        totalFreq = 0;
        totalProbe = 0;
    }
    /**
     * 
     * @param key
     * @param i
     * @return probe index
     */
    public abstract int probingValue( Object key , int i );
    /**
     * Insearching key to the hashtable array and return index
     * @param key
     * @return the index where key was added/ -1 if not
     */
    protected void insert(HashObject key){
        int i = 0;
        int probe = -1;
        while (i <= size){
            probe = probingValue (key.getKey(), i);
            if ( hashTable[probe] == null ){
                hashTable[probe] = key;                
                hashTable[probe].increaseProbe();           
                numElement++;
                totalProbe += i + 1;
                return;
            } else if (hashTable[probe].equals(key.getKey())){
                totalFreq++;
                hashTable[probe].increaseFreq();
                return;
            } else {
                i++;  
                key.increaseProbe();    
              //  totalProbe++;                      
            }

        }

        if ( i > size ){
            throw new RuntimeException("Over loaded hash table");
        }
    }
    /**
     * Searching for key in hashtable array
     * @param key
     * @return Return index where key found or -1 if not found
     */
    protected int search(HashObject key){
        int probe = -1;
        int i = 0;
        while(hashTable[probe] != null || i == size){
            probe = probingValue (key, i);
            if ( hashTable[probe].equals(key)){
                return probe;
            }else{
                i++;
            }
        }
        return probe;
    }
    /**
     * 
     * @param dividend
     * @param divisor
     * @return
     */
    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
            quotient += divisor;
        return quotient;
    }
        


    /**
     * @return int return the numElement
     */
    protected int getNumElement() {
        return numElement;
    }

    /**
     * @return int return the numElement
     */
    protected int getNumFreq() {
        return totalFreq;
    }
    /**
     * Return current factor  num element / table size
     */
    protected double getCurrrentFactor(){
        return (double)numElement / size;
    }
    /**
     * @return int return the totalProbe
     */
    protected int getNumProbe() {
        return totalProbe;
    }

}