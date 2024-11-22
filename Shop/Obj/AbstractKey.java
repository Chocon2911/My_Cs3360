package Obj;

import HuySystem.HuyUtil;

public abstract class AbstractKey extends HuyUtil
{
    private static final String key = "This is private KEY";

    protected String getKey() { return key; }
}
