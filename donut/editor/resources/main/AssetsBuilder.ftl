<#macro tree map>
    <#list map?keys as key>
        <#if map[key]?is_string>
            public static File ${key} = new File("${map[key]}");
        <#else>
            public static class ${key}
            {
                <@tree map[key] />
            }
        </#if>
    </#list>
</#macro>

package ${packageName};

import java.io.File;

public class ${className}
{
    <@tree data />
}



