package club.snow.ihome.designpattern.behavioral.iterator.lang;

/**
 * The type Iterator.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.14
 */
public interface Iterator<E> {

    boolean hasNext();

    E next();
}
