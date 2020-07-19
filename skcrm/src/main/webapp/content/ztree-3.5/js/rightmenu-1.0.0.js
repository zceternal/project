
var rMenu;

function showMenu(treeNode, showRMenu) {
    $("#m_add").show();
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        zTree.cancelSelectedNode();
        showRMenu("root", event.clientX, event.clientY);

    } else if (treeNode && !treeNode.noR) {
        zTree.selectNode(treeNode);

        if (treeNode.children && treeNode.children.length > 0) {
            showRMenu("root", event.clientX, event.clientY);
        } else {
            $("#m_block").hide();
            $("#m_open").hide();
            if (treeNode.state == 0) {
                $("#m_block").show();
            } else if (treeNode.state == 1) {
                $("#m_add").hide();
                $("#m_open").show();
            }
            showRMenu("node", event.clientX, event.clientY);

            if (treeNode.state == 0) {
                $("#m_add").show();
                $("#m_block").show();
            } else if (treeNode.state == 1) {
                $("#m_add").hide();
                $("#m_open").show();
            } else if (treeNode.state == -1) {
                $("#m_add").hide();
                $("#m_del").hide();
            }
        }
    }
}

function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    if (type == "root") {
        $("#m_del").hide();
        $("#m_open").hide();
        $("#m_block").hide();
    } else {
        $("#m_del").show();
    }

    rMenu.css({ "top": y + "px", "left": x + "px", "visibility": "visible" });

    $("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
    if (rMenu) rMenu.css({ "visibility": "hidden" });
    $("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event) {
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        rMenu.css({ "visibility": "hidden" });
    }
}