package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

public class V1_0_2__CreatePcsTable extends BaseJavaMigration {

  @Override
  public void migrate(final Context context) throws Exception {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(context.getConnection());
    jdbcTemplate.execute("CREATE TABLE PCS (\n" +
        "name VARCHAR(255) primary key,\n" +
        "installed_os VARCHAR (255),\n" +
        "processor_name VARCHAR(255),\n" +
        "core_speed DECIMAL,\n" +
        "disc_space_in_GB DECIMAL\n" +
        ");");
  }
}
