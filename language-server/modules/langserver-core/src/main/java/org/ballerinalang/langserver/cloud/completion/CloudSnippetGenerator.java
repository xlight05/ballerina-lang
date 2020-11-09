package org.ballerinalang.langserver.cloud.completion;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemKind;
import org.eclipse.lsp4j.InsertTextFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Code snippets for Cloud configurations.
 */
public class CloudSnippetGenerator {

    private static List<CompletionItem> snippetList = new ArrayList<>();

    static {
        snippetList.add(insertAllCloudConfigs());
    }

    public static CompletionItem insertAllCloudConfigs() {

        String snip = "[container_image]\n" +
                "\n" +
                "repository=\"${1:choreoctrlplane.azurecr.io/choreoipaas}\" # optional default is local\n" +
                "name=\"${2:linker}\" # optional\n" +
                "tag=\"${3:v0.1.1}\"  # default is latest\n" +
                "base=\"${4:openjdk:slim}\"\n" +
                " \n" +
                "\n" +
                "[container_image.user]\n" +
                "\trun_as=\"${5:ballerina}\" # optional. Default is ballerina\n" +
                "\n" +
                "[cloud]\n" +
                "\t\t\n" +
                "\t#This section contains the configs required\n" +
                "[cloud.config]\n" +
                "\t# Simple KV pairs\n" +
                "\tFOO=\"${6:value1}\"\n" +
                "\tBAR=\"${7:value2}\"\n" +
                "\n" +
                "\t# TODO: how to handle secrets/sensitive data\n" +
                "\t\n" +
                "\t# Config files which will be mounted\n" +
                "\t[cloud.config.files]\n" +
                "\t\t[cloud.config.files.ballerina.conf] # optional\n" +
                "\t\t\tfile=\"${8:resource/ballerina.conf}\"\n" +
                "\t\t[cloud.config.files.application1]  # optional\t\n" +
                "\t\t\tfile=\"${9:application1.properties}\"\n" +
                "\t\t\tmount_path=\"${10:/home/ballerina/foo/app1.properties}\"\n" +
                "\t\t[cloud.config.files.application2]  # optional\t\n" +
                "\t\t\tfile=\"${11:application2.properties}\"\n" +
                "\t\t\tmount_path=\"${12:/home/ballerina/bar/app2.properties}\"\n" +
                "\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t# This section specifies deployment related requirements\n" +
                "[cloud.deployment]\n" +
                "\t# Expose this on internal domain\n" +
                "internal_domain_name=\"${13:myfoo}\" # defaults to module name\n" +
                "external_accessible=\"${14:true}\"  #optional default is true\n" +
                "\n" +
                "# runtime requirements\n" +
                "min_memory=\"${15:100Mi}\" # optional\n" +
                "max_memory=\"${16:256Mi}\" # optional\n" +
                "min_cpu=\"${17:1000m}\" # optional\n" +
                "max_cpu=\"${18:1500m}\" # optional\n" +
                "\t\n" +
                "\t[cloud.deployment.autoscaling]\n" +
                "\tenable=\"${19:true|false}\" # default true\n" +
                "\tmin_replicas=\"${20:2}\"\n" +
                "\tmax_replicas=\"${21:3}\"\n" +
                "\tscale_up_condition=”cpu-utilization > 0.75 OR memory-utilization > 0.8”\n" +
                "\n" +
                "[cloud.deployment.probes]\n" +
                "\treadiness=\"${22::9091/readyz}\" \n" +
                "\tliveness=\"${23::9091/healthz}\" \n" +
                "\n" +
                "\n" +
                "\t[cloud.deployment.storage.volumes]\n" +
                "\t\t[cloud.deployment.storage.volumes.foo]\n" +
                "\t\t\tlocal_path=\"${24:/home/ballerina/files}\"\n" +
                "\t\t\tsize=\"${25:2Gi}\"\n" +
                "\t\t\t\n" +
                "\n" +
                "\t\t[cloud.deployment.storage.volumes.bar]\n" +
                "\t\t\tlocal_path=\"${26:/home/ballerina/data}\"\n" +
                "\t\t\tsize=\"${27:500Mi}\"\n";
        CompletionItem item = new CompletionItem();
        item.setLabel("All");
        item.setKind(CompletionItemKind.Snippet);
        item.setDetail("Snippet");
        //item.setSortText("1");
        item.setInsertText(snip);
        item.setInsertTextFormat(InsertTextFormat.Snippet);
        return item;
    }

    private CloudSnippetGenerator() {

    }

    public static List<CompletionItem> getInstance() {

        return snippetList;
    }
}
