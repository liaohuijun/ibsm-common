package com.hm.event.lambda;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hm.event.lambda.Person.PlayBall;

import lombok.Data;
import lombok.ToString;

/**
 * @author shishun.wang
 * @date 2018年3月29日 下午2:19:13
 * @version 1.0
 * @describe
 */
public class MapTest {

	public static void main(String[] args) {

		List<Person> persons = Arrays.asList(new Person("张三", "男", Arrays.asList(new Person.PlayBall("乒乓球", 3), new Person.PlayBall("足球", 1))), new Person("娜娜", "女", Arrays.asList(new Person.PlayBall("棒球", 1), new Person.PlayBall("篮球", 4), new Person.PlayBall("橄榄球", 2))),
				new Person("小明", "女", null), new Person("菲菲", "女", Arrays.asList(new Person.PlayBall("曲棍球球", 5), new Person.PlayBall("足球", 3))));

		List<PlayBall> collect = persons.parallelStream().filter(person -> null != person.getPlayBalls()).flatMap(person -> person.getPlayBalls().stream()).collect(Collectors.toList());
		System.out.println(collect.toString());
		System.out.println("---------------------------------------");
		Map<String, List<PlayBall>> map = persons.parallelStream().filter(person -> null != person.getPlayBalls()).flatMap(person -> person.getPlayBalls().stream()).collect(Collectors.groupingBy(Person.PlayBall::getBallName, Collectors.toList()));
		System.out.println(map.toString());

	}
}

@Data
@ToString
class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String gender;

	private List<PlayBall> playBalls;

	public Person(String name, String gender, List<PlayBall> playBalls) {
		super();
		this.name = name;
		this.gender = gender;
		this.playBalls = playBalls;
	}

	@Data
	@ToString
	public static class PlayBall implements Serializable {

		private static final long serialVersionUID = 1L;

		private String ballName;

		private int year;

		public PlayBall(String ballName, int year) {
			super();
			this.ballName = ballName;
			this.year = year;
		}

	}
}
