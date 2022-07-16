package com.example.demo.repository;


import com.example.demo.vo.CompanyVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// database 와 연결해주는 역할 하는 어노테이션
@Mapper
public interface CompanyMapper {

//    인서트문 동작 어노테이션
//    아래 인서트문에서 한번 컨트롤 빈등록시 에러가 발생했는데
//    빈등록시 sql문을 한번 파싱해서 문제가 있는경우 컴파일에러로 잡아주는데 문자열은 잡지못하고 괄호 부분만 구분하는듯하다.
//    @Param은 alias를 의미함
    @Insert("INSERT INTO company(company_name, company_address) VALUES(#{company.companyName}, #{company.companyAddress})")
//    반환값에 int로 1,0을 받아 성공여부를 파악하고 companyVo 객체를 전달
    int insert(@Param("company") CompanyVo companyVo);
}
