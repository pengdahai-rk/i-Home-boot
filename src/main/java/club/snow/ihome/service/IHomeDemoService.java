package club.snow.ihome.service;

/**
 * The interface Home demo service.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.16
 */
public interface IHomeDemoService {

    /**
     * Gets string.
     *
     * @param words the words
     * @return the string
     */
    String getString(String words);


    String transactionTest(String words);


    String transactionTest2(String words);
}
