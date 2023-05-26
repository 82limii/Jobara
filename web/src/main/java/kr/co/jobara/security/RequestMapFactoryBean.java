package kr.co.jobara.security;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RequestMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
	private DataSource dataSource;

	@Required
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap;

	@PostConstruct
	public void init() {
		requestMap = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT B.RESOURCE_PATTERN pattern, A.AUTHORITY authority ");
		sql.append(" FROM SECURED_RESOURCES_ROLE A INNER JOIN SECURED_RESOURCES B ON ");
		sql.append(" (A.RESOURCE_ID = B.RESOURCE_ID) ");
		List<Map<String, Object>> resourcesList = template.queryForList(sql.toString(), new MapSqlParameterSource());
		if (resourcesList.size() > 0) {
			for (Map<String, Object> mappingInfo : resourcesList) {
				String pattern = (String) mappingInfo.get("pattern");
				String authority = (String) mappingInfo.get("authority");
				RequestMatcher matcher = new AntPathRequestMatcher(pattern);
				List<ConfigAttribute> attributes = null;
				attributes = requestMap.get(matcher);
				if (attributes == null) {
					attributes = new LinkedList<ConfigAttribute>();
				}
				attributes.add(new SecurityConfig(authority));
				requestMap.put(matcher, attributes);
			}
		}
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
		if (requestMap == null) {
			init();
		}
		return requestMap;
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
