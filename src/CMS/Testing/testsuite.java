package CMS.Testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import CMS.Testing.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({ 
	account.class,shipment.class,statement_coverage.class
})

public class testsuite {

}
