package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private  static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
//        DB.add(person);
        System.out.println("insertPerson Fake");
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPerson() {
//        System.out.println("Fake");
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personDel = selectPersonById(id);
        if(personDel == null ){
            return  0 ;
        }
        DB.remove(personDel.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id).map(p -> {
                    int indexOfPersonList = DB.indexOf(p);
                    if (indexOfPersonList >= 0){
//                        DB.set(indexOfPersonList,person);   // use this make id is null because id from parameter(person) didn't generated
                                                            // the id was generated from method insert person

                        DB.set(indexOfPersonList,new Person(id,person.getName())); // so for correct > new person by id(old) and name(new)
                        return  1;
                    }
                    return  0 ;
                }
        ).orElse(0);
    }

}
