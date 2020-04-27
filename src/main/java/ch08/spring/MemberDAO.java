package ch08.spring;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;


public class MemberDAO {
    private JdbcTemplate jdbcTemplate;
    public MemberDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Member selectByEmail(String email){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                (resultSet, i) -> {
                    Member member = new Member(
                            resultSet.getString("EMAIL"),
                            resultSet.getString("PASSWORD"),
                            resultSet.getString("NAME"),
                            resultSet.getTimestamp("REGDATE").toLocalDateTime());
                    member.setId(resultSet.getLong("ID"));
                    return member;
                },email);
        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String sql = "insert into MEMBER(email, password, name, regdate) " +
                    "values (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"ID"});
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
            return pstmt;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        assert keyValue != null;
        member.setId(keyValue.longValue());
    }

    public void update(Member member){
        jdbcTemplate.update("update MEMBER set NAME=?, PASSWORD=? WHERE EMAIL=?",
                member.getName(), member.getPassword(), member.getEmail());
    }

    public List<Member> selectAll(){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER",
                (rs, i) -> {
                    Member member = new Member(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getTimestamp("REGDATE").toLocalDateTime());
                    member.setId(rs.getLong("ID"));
                    return member;
                }
        );
        return results.isEmpty() ? null : results;
    }

    public int count(){
        return jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class
        );

    }

    public Member selectById(long id){
        return jdbcTemplate.queryForObject(
                "select * from MEMBER where ID = ?",
                (resultSet, i) -> {
                    Member member1 = new Member(
                            resultSet.getString("EMAIL"),
                            resultSet.getString("PASSWORD"),
                            resultSet.getString("NAME"),
                            resultSet.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    member1.setId(resultSet.getLong("ID"));
                    return member1;
                }, id);
    }

    public void deleteMember(Member member) {
        jdbcTemplate.update("delete from member where email = ?", member.getEmail());
    }
}
