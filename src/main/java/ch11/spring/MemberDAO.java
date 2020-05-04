package ch11.spring;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


public class MemberDAO {
    private JdbcTemplate jdbcTemplate;
    public MemberDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Member> memberRowMapper = ((resultSet, i) -> {
        Member member = new Member(
                resultSet.getString("EMAIL"),
                resultSet.getString("PASSWORD"),
                resultSet.getString("NAME"),
                resultSet.getTimestamp("REGDATE").toLocalDateTime()
        );
        member.setId(resultSet.getLong("ID"));
        return member;
    });
    public Member selectByEmail(String email){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                memberRowMapper, email);
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
                "select * from MEMBER", memberRowMapper);
        return results.isEmpty() ? null : results;
    }

    public int count(){
        return jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class
        );

    }

    public Member selectById(long id){
        List<Member> members = jdbcTemplate.query(
                "select * from MEMBER where ID = ?", memberRowMapper, id
        );
        return members.isEmpty() ? null : members.get(0);
    }

    public void deleteMember(Member member) {
        jdbcTemplate.update("delete from member where email = ?", member.getEmail());
    }

    public List<Member> selectByRegDate(LocalDateTime from, LocalDateTime to){
        return jdbcTemplate.query("select * from MEMBER where REGDATE between ? and ?" +
                "order by REGDATE desc", memberRowMapper, from, to);
    }
}
