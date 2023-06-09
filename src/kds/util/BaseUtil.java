package kds.util;

public class BaseUtil {
    public static int simpleHash(int hashNum, int key) {
        return key % hashNum;
    }
}
