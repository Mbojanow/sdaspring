package pl.sdacademy.wiosnademo.migrations;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V1_0_2__springJdbcMigration extends BaseJavaMigration {
  @Override
  public void migrate(final Context context) throws Exception {
    final JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
    jdbcTemplate.execute("SELECT 2");
  }
}
