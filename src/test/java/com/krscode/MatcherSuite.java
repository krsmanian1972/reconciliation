package com.krscode;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({DataMatcherShould.class, ReconciliationShould.class})
public final class MatcherSuite {

}