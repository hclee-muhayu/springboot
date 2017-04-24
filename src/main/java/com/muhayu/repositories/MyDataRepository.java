package com.muhayu.repositories;

import com.muhayu.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hyecheon on 2017. 4. 16..
 */
@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
    MyData findById(Long name);
}
