import java.util.Comparator;
import java.util.*;

/**
 * Factor pattern for obtaining PrefixComparator objects
 * without calling new. Users simply use
 *
 *     Comparator<Term> comp = PrefixComparator.getComparator(size)
 *
 
 */
public class PrefixComparator implements Comparator<Term> {

    private int myPrefixSize; // size of prefix

    /**
     * private constructor, called by getComparator
     * @param prefix is prefix used in compare method
     */
    private PrefixComparator(int prefix) {
        myPrefixSize = prefix;
    }


    /**
     * Factory method to return a PrefixComparator object
     * @param prefix is the size of the prefix to compare with
     * @return PrefixComparator that uses prefix
     */
    public static PrefixComparator getComparator(int prefix) {
        return new PrefixComparator(prefix);
    }

    @Override
    /**
     * Use at most myPrefixSize characters from each of v and w
     * to return a value comparing v and w by words. Comparisons
     * should be made based on the first myPrefixSize chars in v and w.
     * @return < 0 if v < w, == 0 if v == w, and > 0 if v > w
     */
    public int compare(Term v, Term w) {
        if (v.getWord().length() >= myPrefixSize && w.getWord().length() >= myPrefixSize) {
            String vSub = v.getWord().substring(0, myPrefixSize);
            String wSub = w.getWord().substring(0, myPrefixSize);
            if (vSub.equals(wSub)) {
                return 0;
            }

            return vSub.compareTo(wSub);
        }
        if (v.getWord().length() < myPrefixSize || w.getWord().length() < myPrefixSize) {
            if (PrefixComparator.shortest(v, w) == v.getWord()) {
                String vSub = v.getWord().substring(0, v.getWord().length()-1);
                String wSub = w.getWord().substring(0, v.getWord().length()-1);
                if (vSub.equals(wSub)) {
                    return -1;
                }
                return vSub.compareTo(wSub);
            }
            if (PrefixComparator.shortest(v, w).equals(w.getWord())) {
                String vSub = v.getWord().substring(0, w.getWord().length()-1);
                String wSub = w.getWord().substring(0, w.getWord().length()-1);
                if (wSub.equals(vSub)) {
                    return 1;
                }
                return vSub.compareTo(wSub);
            }
        }
    return 0;
}
     private static String shortest(Term v, Term w) {
        if (v.getWord().length() < w.getWord().length()) {
            return v.getWord();
        }
        if (v.getWord().length() == w.getWord().length()){
            return "equal";
        }

        return w.getWord();
    }
}
    