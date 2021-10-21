package vn.edu.fithou.quanlychitieu;

import org.junit.Test;

import vn.edu.fithou.quanlychitieu.util.ResolveSMSUtil;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        ResolveSMSUtil.isBank("vietcomBank");
    }
}