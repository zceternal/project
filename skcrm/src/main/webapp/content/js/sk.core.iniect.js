$.ajaxPrefilter(function(options, originalOptions, xhr) {

	var success = options.success;

	options.success = function(data, textStatus, xhr) {
		if (success) {

			// 不验证script
			if (options.dataType == "script") {
				success(data, textStatus, xhr);
				return;
			}
			var json = data;
			if (options.dataType != "json") {
				try {
					json = JSON.parse(data);
				} catch (e) {

				}
			}
			if (json && json.isNotLogin) {
				$.sk.closeAll();
				$.sk.error("您已经掉线，请重新登录！");

				return;
			}

			if (json && json.isNotAuth) {
				$.sk.closeAll();
				$.sk.error("您的权限已改变，请重新登录或者联系管理员");
				return;
			}
			if (json && json.systemError == "1") {
				$.sk.closeAll();
				$.sk.error("对不起，您的数据已经不存在！");
				return;
			}

			if (json && json.systemError) {
				$.sk.closeAll();
				$.sk.error(json.systemError);
				return;
			}
			success(data, textStatus, xhr);
		}
	};

});

var getScrollBarHW = function() { // 获取浏览器的宽度和高度对象
	if (this.scrollBarHW) {
		return this.scrollBarHW;
	}
	var div = document.createElement('div');
	div.style.overflow = 'scroll';
	div.style.visibility = 'hidden';
	div.style.position = 'absolute';
	div.style.width = '100px';
	div.style.height = '100px';
	// div.style.cssText = 'overflow:scroll;width:100px;height:100px;';
	document.body.appendChild(div);

	this.scrollBarHW = {
		width : div.offsetWidth - div.clientWidth,
		height : div.offsetHeight - div.clientHeight
	};
	div.parentNode.removeChild(div);
	return this.scrollBarHW;
}

Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d{1})}/g, function() {
		return args[arguments[1]];
	});
};

String.prototype.leave = function() {
	var args = arguments;
	if (this.length <= args[0]) {
		return this;
	}
	return this.substring(0, args[0]) + "...";
};

String.prototype.text = function() {
	return this.replace(/[<>&"]/g, function(c) {
		return {
			'<' : '&lt;',
			'>' : '&gt;',
			'&' : '&amp;',
			'"' : '&quot;'
		}[c];
	});
};

String.prototype.html = function() {
	var arrEntities = {
		'lt' : '<',
		'gt' : '>',
		'nbsp' : ' ',
		'amp' : '&',
		'quot' : '"'
	};
	return this.replace(/&(lt|gt|nbsp|amp|quot);/ig, function(all, t) {
		return arrEntities[t];
	});
};

Date.prototype.add = function(interval, number) {
	/*
	 * 功能:实现VBScript的DateAdd功能. 参数:interval,字符串表达式，表示要添加的时间间隔.
	 * 参数:number,数值表达式，表示要添加的时间间隔的个数. 参数:date,时间对象. 返回:新的时间对象. var now = new-----------
	 */
	switch (interval) {
	case "y": {
		this.setFullYear(this.getFullYear() + number);
		return date;
		break;
	}
	case "q": {
		this.setMonth(this.getMonth() + number * 3);
		return date;
		break;
	}
	case "m": {
		this.setMonth(this.getMonth() + number);
		return date;
		break;
	}
	case "w": {
		this.setDate(this.getDate() + number * 7);
		return date;
		break;
	}
	case "d": {
		this.setDate(this.getDate() + number);
		return date;
		break;
	}
	case "h": {
		this.setHours(this.getHours() + number);
		return date;
		break;
	}
	case "m": {
		this.setMinutes(this.getMinutes() + number);
		return date;
		break;
	}
	case "s": {
		this.setSeconds(this.getSeconds() + number);
		return date;
		break;
	}
	default: {
		this.setDate(this.getDate() + number);
		return date;
		break;
	}
	}
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"H+" : this.getHours() + 12, // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
	_title : function(title) {
		var $title = this.options.title || '&nbsp;'
		if (("title_html" in this.options) && this.options.title_html == true)
			title.html($title);
		else
			title.text($title);
	}
}));

var Url = {};
Url.addParam = function(url, name, value) {
	return url
			+ (url.indexOf("?") > -1 ? "&" + name + "=" + value : "?" + name
					+ "=" + value);
};

function disabledDelay($this) {
	$this.attr("disabled", true);
	setTimeout(function() {
		$this.attr("disabled", false);
	}, 1000);
}

function autoHeight() {
	// 自动高度计算
	var height = $(window).height();
	$("div[data-heigh]").each(function() {
		var $this = $(this);
		var minHeight = height - $this.data("heigh");
		if (minHeight < $this.height()) {
			$this.css("overflow-y", "scroll");
		}
		$this.css("overflow-x", "hidden");
		$this.height(minHeight);
	});
}

// 全屏js
function launchFullscreen(element) {
	if (element.requestFullscreen) {
		element.requestFullscreen();
	} else if (element.mozRequestFullScreen) {
		element.mozRequestFullScreen();
	} else if (element.webkitRequestFullscreen) {
		element.webkitRequestFullscreen();
	} else if (element.msRequestFullscreen) {
		element.msRequestFullscreen();
	}
	$(document.body).css("background-color", "#fff");
}

$(function() {
	autoHeight();
	$(window).resize(function() {
		setTimeout(function() {
			autoHeight();
		}, 200);
	});

	$(".sk_checkbox").live("click", function() {

		var $this = $(this);

		if ($this.attr("checked")) {
			$this.parent('label').addClass('c_on');
		} else {
			$this.parent('label').removeClass('c_on');
		}
		return true;
	});

	// 全选、
	$("body").delegate(
			":checkbox[data-for]",
			"click",
			function() {

				var $this = $(this);
				var state = $this.attr("checked") == undefined ? false : true;
				$(":checkbox[data-client='" + $this.data("for") + "']").attr(
						"checked", state);
				if (state) {
					$(":checkbox[data-client='" + $this.data("for") + "']")
							.parent('label').addClass('c_on');
					$this.parent('label').addClass('c_on');
				} else {
					$(":checkbox[data-client='" + $this.data("for") + "']")
							.parent('label').removeClass('c_on');
					$this.parent('label').removeClass('c_on');
				}

			});

	$(":checkbox[data-client]")
			.live(
					"click",
					function() {
						var $this = $(this);
						var size = $(
								":checkbox[data-client='"
										+ $this.data("client") + "']").size();
						var checkedSize = $(
								":checkbox[data-client='"
										+ $this.data("client") + "']:checked")
								.size();
						var $parentCheck = $(":checkbox[data-for='"
								+ $this.data("client") + "']");
						if (size == checkedSize) {

							$parentCheck.attr("checked", true);
							$parentCheck.parent('label').addClass('c_on');

						} else {
							$parentCheck.attr("checked", false);
							$parentCheck.parent('label').removeClass('c_on');
						}

					});

	$("select[data-value]").find("option").each(function() {
		var $this = $(this);
		if ($this.attr("value") == $this.parent().data("value")) {
			$this.attr("selected", true);
		}
	});

	// 自动聚焦
	$(".cursor").focus();

	$("body").delegate(":button[data-delay=true]", "click", function() {
		disabledDelay($(this));
	});
	$("body").delegate(".delay", "click", function() {
		disabledDelay($(this));
	});

});
