package club.snow.ihome.designpattern.test;

import club.snow.ihome.designpattern.behavioral.iterator.group.Employee;
import club.snow.ihome.designpattern.behavioral.iterator.group.GroupStructure;
import club.snow.ihome.designpattern.behavioral.iterator.group.Link;
import club.snow.ihome.designpattern.behavioral.iterator.lang.Iterator;
import lombok.extern.slf4j.Slf4j;

/**
 * The type DesignPatternTest.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.14
 */
@Slf4j
public class DesignPatternTest {

    public static void main(String[] args) {
        testIterator();
    }

    public static void testIterator() {
        // 数据填充
        GroupStructure groupStructure = new GroupStructure("1", "⼩傅哥");

        // 雇员信息
        groupStructure.add(new Employee("2", "花花", "⼆级部⻔"));
        groupStructure.add(new Employee("3", "⾖包", "⼆级部⻔"));
        groupStructure.add(new Employee("4", "蹦蹦", "三级部⻔"));
        groupStructure.add(new Employee("5", "⼤烧", "三级部⻔"));
        groupStructure.add(new Employee("6", "⻁哥", "四级部⻔"));
        groupStructure.add(new Employee("7", "玲姐", "四级部⻔"));
        groupStructure.add(new Employee("8", "秋雅", "四级部⻔"));

        // 节点关系 1->(1,2) 2->(4,5)
        groupStructure.addLink("1", new Link("1", "2"));
        groupStructure.addLink("1", new Link("1", "3"));
        groupStructure.addLink("2", new Link("2", "4"));
        groupStructure.addLink("2", new Link("2", "5"));
        groupStructure.addLink("5", new Link("5", "6"));
        groupStructure.addLink("5", new Link("5", "7"));
        groupStructure.addLink("5", new Link("5", "8"));
        Iterator<Employee> iterator = groupStructure.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            log.info("{}，雇员 Id：{} Name：{}", employee.getDesc(), employee.getUserId(), employee.getName());
        }
    }
}
