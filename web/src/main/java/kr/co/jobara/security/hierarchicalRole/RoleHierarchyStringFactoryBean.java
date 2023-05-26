package kr.co.jobara.security.hierarchicalRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 데이터베이스의 권한 계층 구조를 조회하여 계층구조 문자열을 생성하는 FactoryBean.<br />
 * 권한계층구조 문자열의 형태는 다음과 같다.
 * 
 * <pre>
* ROLE_ADMIN > ROLE_USER
* ROLE_USER > ROLE_RESTRICTED
* ROLE_RESTRICTED > IS_AUTHENTICATED_FULLY
 * </pre>
 */

public class RoleHierarchyStringFactoryBean implements FactoryBean<String> {
	private DataSource dataSource;

	@Required
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	String ROLE_HIERARCHY_QUERY = " SELECT CHILD_ROLE || ' > ' || PARENT_ROLE HIERARYSTRING " + " FROM ROLES_HIERARCHY "
			+ " START WITH CHILD_ROLE = 'ROLE_ADMIN' " + " CONNECT BY PRIOR PARENT_ROLE = CHILD_ROLE ";

	@Override
	public String getObject() throws Exception {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		List<Map<String, Object>> hierarchy = template.queryForList(ROLE_HIERARCHY_QUERY,
				new HashMap<String, String>());
		StringBuffer buffer = new StringBuffer();
		for (Map<String, Object> record : hierarchy) {
			buffer.append(record.get("HIERARYSTRING") + "\n");
		}
		return buffer.toString();
	}

	@Override
	public Class<?> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
