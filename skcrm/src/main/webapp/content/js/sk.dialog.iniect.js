
(function ($) {
    $.sk = {};

    $.sk.dialogs = [];

    function maxIndex() {
        return Math.max.apply(null, $.map($('body > *'), function (e, n) {
            if ($(e).css('position') == 'fixed')
                return parseInt($(e).css('z-index')) || 1;
            return -999;
        })
       );
    }

    $.sk.closeAll = function () {
        for (var i = 0; i < $.sk.dialogs.length; i++) {
            $.sk.close($.sk.dialogs[i]);
        }
    };

    $.sk.close = function ($dialog,callback) {
        $dialog.dialog("close");
        $dialog.remove();
        if(callback != undefined){
        	callback();
        }
    };

    $.sk.success = function (html, callback) {
        var id = $.md5(Math.random() + "");//fa fa-times
        html = "<div class=\"alert alert-success bigger-110 no-margin-bottom\"><i class=\"ace-icon glyphicon glyphicon-ok\"></i><span>" + html + "</span></div>";
        $(document.body).append($("<div id=" + id + " class=\"hide\">" + html + "</div>"));
        var $dialog = $("#" + id).removeClass('hide').dialog({
            resizable: false,
            width: 520,
            closeText: "关闭",
            modal: true,
            title: "<div class='widget-header'><h4 class='smaller'>" + "系统提示" + "</h4></div>",
            title_html: true,
            close: function (event, ui) {
                $(this).remove();
                $.sk.dialogs.remove($dialog);
                if (callback) callback();
            },
            buttons: [
                {
                    html: "关闭",
                    "class": "btn btn-danger btn-minier",
                    click: function () {
                        $(this).dialog("close");
                    }
                }
            ]
        });
        $.sk.dialogs.push($dialog);
    };

    $.sk.confirm = function (html, callback) {
        var id = $.md5(Math.random() + "");
        html = "<div class=\"alert alert-warning bigger-110 no-margin-bottom\"><i class=\"glyphicon glyphicon-warning-sign\"></i><span>" + html + "</span></div>";
        $(document.body).append($("<div id=" + id + " class=\"hide\">" + html+ "</div>"));
        var $dialog = $("#" + id).removeClass('hide').dialog({
            resizable: false,
            width: 520,
            modal: true,
            closeText: "关闭",
            title: "<div class='widget-header'><h4 class='smaller'>" + "系统提示" + "</h4></div>",
            title_html: true,
            close: function (event, ui) {
                $.sk.dialogs.remove($dialog);
                $(this).remove();
                callback(false);
            },
            buttons: [
                {
                    html: "确定",
                    "class": "btn btn-minier  btn-success delay",
                    click: function () {
                        $(this).dialog("close");
                        $(this).remove();
                        callback(true);
                    }
                },
                    {
                        html: "关闭",
                        "class": "btn btn-danger btn-minier",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
            ]
        });
        $.sk.dialogs.push($dialog);
    };

    $.sk.error = function (html, callback) {
        var id = $.md5(Math.random() + "");
        html = "<div class=\"alert alert-danger bigger-110 no-margin-bottom\"><i class=\"glyphicon glyphicon-info-sign\"></i><span>" + html + "</span></div>";
        $(document.body).append($("<div id=" + id + " class=\"hide\">" + html + "</div>"));
        var $dialog = $("#" + id).removeClass('hide').dialog({
            resizable: false,
            width: 520,
            modal: true,
            closeText: "关闭",
            title: "<div class='widget-header'><h4 class='smaller'>" + "系统提示" + "</h4></div>",
            title_html: true,
            close: function (event, ui) {
                $.sk.dialogs.remove($dialog);
                $(this).remove();
                if (callback) callback();
            },
            buttons: [
                {
                    html: "关闭",
                    "class": "btn btn-danger btn-minier",
                    click: function () {
                        $(this).dialog("close");
                    }
                }
            ]
        });
        $.sk.dialogs.push($dialog);
    };
    
    $.sk.openHTML = function (options) {

        var dialog = null;

        var settings = $.extend({
            'title': '系统提示',
            'html': '',
            'width': 500,
            'height': "auto",
            'resizable': false,
            'cache': false,
            'buttons': [],
            'data': null
        }, options);

        settings.buttons.push({
            html: "关闭",
            "class": "btn btn-danger btn-minier",
            click: function () {
                $(this).dialog("close");
            }
        });

        var id = $.md5(settings.title);

        var $div = $("#" + id);

        if ($div.size() > 0) {
            if (!settings.cache) {
                $div.empty();
            }
        } else {
            $(document.body).append($("<div id=" + id + ">" + "<div class=\"page-content\">" + settings.html + "</div></div>"));
        }

        dialog = $("#" + id).dialog({
            resizable: settings.resizable,
            width: settings.width,
            height: settings.height,
            modal: true,
            closeText: "关闭",
            close: function (event, ui) {
                $.sk.dialogs.remove(dialog);
                $(this).remove();
            },
            title: "<div class='widget-header'><h4 class='smaller'>" + settings.title + "</h4></div>",
            title_html: true,
            buttons: settings.buttons
        });
        $.sk.dialogs.push(dialog);
        return dialog;
    };
    
    $.sk.load = function (options) {

        var dialog = null;

        var settings = $.extend({
            'title': '系统提示',
            'html': '数据正在加载中',
            'width': 200,
            'height': "auto",
            'resizable': false,
            'cache': false,
            'buttons': [],
            'data': null,
            id: $.md5(Math.random() + "")
        }, options);

        settings.buttons.push({
            html: "关闭",
            "class": "btn btn-danger btn-minier",
            click: function () {
                $(this).dialog("close");
            }
        });

        var id = settings.id;

        $(document.body).append($("<div id=" + id + ">" + "<div class=\"page-content text-center\"><i class=\"ace-icon fa fa-spinner fa-spin blue bigger-300 middle\"></i><span class=\"middle\">&nbsp;" + settings.html + "</span>" + "</div></div>"));
        
        dialog = $("#" + id).dialog({
            resizable: settings.resizable,
            width: settings.width,
            height: settings.height,
            modal: true,
            closeText: "关闭",
            close: function (event, ui) {
                $.sk.dialogs.remove(dialog);
                $(this).remove();
            },
            title: "<div class='widget-header'><h4 class='smaller'>" + settings.title + "</h4></div>",
            title_html: true
        });
        $.sk.dialogs.push(dialog);
        return dialog;


    };

    $.sk.open = function (options) {

        var dialog = null;
        var rid = "D" + $.md5(Math.random() + "");
        var settings = $.extend({
            'title': '系统提示',
            'url': 'about:blank',
            'width': 500,
            'height': "auto",
            'resizable': false,
            'cache': false,
            'buttons': [],
            'data': null,
            'id': rid,
            'begin': function () { },
            'end': function () { },
            'delay': 0
        }, options);

        settings.buttons.push({
            html: "关闭",
            "class": "btn btn-danger btn-minier",
            click: function () {
                $(this).dialog("close");
            }
        });

        
        var id = settings.id;

        var $div = $("#" + id);
    
        if ($div.size() > 0) {
            if (!settings.cache) {
                $div.empty();
            }
        } else {
            $(document.body).append($("<div id=" + id + " class=\"loadingpage\">" + "<div class=\"page-content text-center\"><i class=\"ace-icon fa fa-spinner fa-spin blue bigger-300 middle\"></i><span class=\"middle\">&nbsp;数据正在加载中</span>" + "</div></div>"));
        }

        var $body = $(document.body);
        
        dialog = $("#" + id).dialog({
            resizable: settings.resizable,
            width: 200,
            height: "auto",
            modal: true,
            closeText: "关闭",
            close: function (event, ui) {
                try {

                    if ($.sk.dialogs.length <= 1) {
                        var width = $body.width();
                        $body.toggleClass("html-body-overflow", false).width(width);
                    }
                    settings.end();
                } catch (e) {
                    if (console && console.e) {
                        console.e(e);
                    }
                }
                $.sk.dialogs.remove(dialog);
                $(this).empty();
                $(this).remove();

            },
            open: function (event, ui) {
                var width = $body.width();
                $body.toggleClass("html-body-overflow", true).width(width);

            },
            title: "<div class='widget-header'><h4 class='smaller'>" + settings.title + "</h4></div>",
            title_html: true
        });

        if (!settings.cache) {
            $.ajax({
                type: "GET",
                url: Url.addParam(settings.url, "t", Math.random()),
                data: settings.data,
                dataType: "html",
                success: function (html) {
                    dialog.dialog("option", "width", settings.width);
                    dialog.dialog("option", "buttons", settings.buttons);

                    try {
                        $("#" + id).removeClass("loadingpage").html(html);
                        
                    } catch (e) {
                        if (console && console.e) {
                            console.e(e);
                        }
                    }
                    
                    settings.begin();
                  
                    setTimeout(function () {
                    
                    if (settings.height == "auto") {
                       
                        var winHeight = screen.height;

                        
                        if (winHeight - 132 - 150 < $("#" + id).height()) {
                            dialog.dialog("option", "height", (winHeight - 132 - 120) * 0.9);
                        }
                        
                    } else {
                        
                        dialog.dialog("option", "height", settings.height);
                    }

                    $("#" + id).find(".cursor").focus();
                    

                    dialog.dialog("option", "position", { my: "center", at: "center", of: window });

                    }, settings.delay);
                }
            });
        }
        $.sk.dialogs.push(dialog);
        return dialog;


    };
    

    $.sk.show = function (options) {

        var dialog = null;

        var settings = $.extend({
            'title': '系统提示',
            'url': 'about:blank',
            'width': 500,
            'height': "auto",
            'resizable': false,
            'cache': false,
            'buttons': [],
            'data': null,
            "close": function () { },
            'begin': function () { },
            'end': function () { },
            'delay': 0
        }, options);


        var id = $.md5(settings.url);

        var $div = $("#" + id);

        if ($div.size() > 0) {
            if (!settings.cache) {
                $div.empty();
            }
        } else {
            $(document.body).append($("<div id=" + id + ">" + "<div class=\"page-content text-center\"><i class=\"ace-icon fa fa-spinner fa-spin blue bigger-300 middle\"></i><span class=\"middle\">&nbsp;数据正在加载中</span>" + "</div></div>"));
        }
        var $body = $(document.body);
        dialog = $("#" + id).dialog({
            resizable: settings.resizable,
            width: 200,
            height: "auto",
            modal: true,
            closeText: "关闭",
            close: function (event, ui) {
                try {

                    if ($.sk.dialogs.length <= 1) {
                        var width = $body.width();
                        $body.toggleClass("html-body-overflow", false).width(width);
                    }
                    settings.end();
                } catch (e) {
                    if (console && console.e) {
                        console.e(e);
                    }
                }
                $.sk.dialogs.remove(dialog);
                $(this).empty();
                $(this).remove();
                settings.close();
            },
            open: function (event, ui) {
                var width = $body.width();
                $body.toggleClass("html-body-overflow", true).width(width);

            },
            title: "<div class='widget-header'><h4 class='smaller'>" + settings.title + "</h4></div>",
            title_html: true
        });

        if (!settings.cache) {
            $.ajax({
                type: "GET",
                url: Url.addParam(settings.url, "t", Math.random()),
                data: settings.data,
                dataType: "html",
                success: function (html) {

                    dialog.dialog("option", "width", settings.width);
                    dialog.dialog("option", "buttons", settings.buttons);
                    $("#" + id).html(html);
                    
                    settings.begin();

                    setTimeout(function () {

                        if (settings.height == "auto") {

                            var winHeight = screen.height;

                            if (winHeight - 132 - 130 < $("#" + id).height()) {
                                dialog.dialog("option", "height", (winHeight - 132 - 130) * 0.9);
                            }

                        } else {

                            dialog.dialog("option", "height", settings.height);
                        }

                        $("#" + id).find(".cursor").focus();


                        dialog.dialog("option", "position", { my: "center", at: "center", of: window });

                    }, settings.delay);
                }
            });
        }
        $.sk.dialogs.push(dialog);
        return dialog;


    };
    
    $.sk.alert = function (html, callback) {
        var id = $.md5(Math.random() + "");
        html = "<div class=\"alert alert-warning bigger-110 no-margin-bottom\"><i class=\"glyphicon glyphicon-warning-sign \"></i>  " + html + "</div>";
        $(document.body).append($("<div id=" + id + " class=\"hide\">" + html + "</div>"));
        
        var $dialog = $("#" + id).removeClass('hide').dialog({
            resizable: false,
            width: 320,
            modal: true,
            closeText: "关闭",
            title: "<div class='widget-header'><h4 class='smaller'>" + "系统提示" + "</h4></div>",
            title_html: true,
            close: function (event, ui) {
                $.sk.dialogs.remove($dialog);
                $(this).remove();
                if (callback) callback();
            },
            buttons: [
                {
                    html: "确定",
                    "class": "btn btn-minier btn-success delay",
                    click: function () {

                        $(this).dialog("close");
                    }
                }
            ]
        });
        $.sk.dialogs.push($dialog);
    };


})(jQuery);
