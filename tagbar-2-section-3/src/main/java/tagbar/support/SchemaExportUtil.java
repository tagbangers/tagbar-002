package tagbar.support;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import tagbar.entity.Event;

import java.util.EnumSet;

public class SchemaExportUtil {

	public static void main(String[] args) {
		MetadataSources metadata = new MetadataSources(
				new StandardServiceRegistryBuilder()
						.applySetting(AvailableSettings.DIALECT, H2Dialect.class)
						.build());

		metadata.addAnnotatedClass(Event.class);

		SchemaExport export = new SchemaExport();

		export.setDelimiter(";");
		export.setFormat(true);
		export.createOnly(EnumSet.of(TargetType.STDOUT), metadata.buildMetadata());
	}
}
