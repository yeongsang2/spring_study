package hello.hellospring.domain;
import javax.persistence.*;

@Entity //jpa가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) //db가 알아서 생성해주는거 IDENTITY
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
