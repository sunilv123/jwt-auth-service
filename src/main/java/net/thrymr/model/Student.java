package net.thrymr.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Student")
public class Student implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4678500393774419941L;

	public enum Gender {
        MALE, FEMALE
    }

	//@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private Gender gender;
    private int grade;

    public Student(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    
    
    
    public Student() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Student(String name, Gender gender, int grade) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", gender=" + gender + ", grade=" + grade + '}';
    }
}
