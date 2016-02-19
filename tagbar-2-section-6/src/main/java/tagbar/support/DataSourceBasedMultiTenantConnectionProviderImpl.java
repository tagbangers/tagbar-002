package tagbar.support;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private DataSource dataSource1;

	private DataSource dataSource2;

	private Map<String, DataSource> dataSourceMap = new HashMap<>();

	public void DataSourceBasedMultiTenantConnectionProviderImpl(DataSource dataSource1, DataSource dataSource2) {
		dataSourceMap.put(CurrentTenantIdentifierResolverImpl.TENANT_1_IDENTIFIER, dataSource1);
		dataSourceMap.put(CurrentTenantIdentifierResolverImpl.TENANT_2_IDENTIFIER, dataSource2);
	}

	@Override
	protected DataSource selectAnyDataSource() {
		return dataSource1;
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		return dataSourceMap.get(tenantIdentifier);
	}
}