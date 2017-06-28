package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import system.SceneManager;

public class DodgeRankDAO {
	private BasicDataSource ds;
	
	public DodgeRankDAO(){
		ds = new BasicDataSource();
		
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		
		ds.setInitialSize(4);
	}
	
	public int insert(DodgeRankDTO dto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			String sql = "insert into dodge_rank(dname, drecord) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getDname());
			//pstmt.setString(2, dto.getDdate());
			pstmt.setInt(2, dto.getDrecord());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally{
			if(con != null) try{con.close();}catch(SQLException e){}
		}
		
		return -1;
	}
	
	public ArrayList<DodgeRankDTO> select(int numberOfImports){
		ArrayList<DodgeRankDTO> list = new ArrayList<DodgeRankDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			String sql = "select * from(select * from dodge_rank order by drecord desc, ddate asc) where rownum <= " + numberOfImports;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				DodgeRankDTO dto = new DodgeRankDTO();
				
				dto.setDname(rs.getString("dname"));
				dto.setDdate(rs.getString("ddate"));
				dto.setDrecord(rs.getInt("drecord"));
				
				list.add(dto);
				
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(con != null){
				try{	con.close();	}catch(SQLException sqle){}
			}
		}
		
		return null;
	}
	
	public int updat(DodgeRankDTO dto){
		
		return -1;
	}
	
	public int delete(String date){
		
		return -1;
	}
}
