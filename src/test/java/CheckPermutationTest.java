import com.codeallday.arrays.strings.CheckPermutation;
import org.junit.Test;

/**
 * Created by ypatel on 3/23/17.
 */
public class CheckPermutationTest {

    @Test
    public void printCheckPermutation() {
        CheckPermutation checkPermutation = new CheckPermutation();
        checkPermutation.printMe();
    }

    @Test
    public void testIteratorCheckPermutation() {
        CheckPermutation checkPermutation = new CheckPermutation();
        checkPermutation.testIterator();
    }
}
