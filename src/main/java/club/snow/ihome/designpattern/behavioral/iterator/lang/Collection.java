package club.snow.ihome.designpattern.behavioral.iterator.lang;

/**
 * The type Collection.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.14
 */
public interface Collection<E, L> extends Iterable<E> {

    boolean add(E e);

    boolean remove(E e);

    boolean addLink(String key, L l);

    boolean removeLink(String key);

}
