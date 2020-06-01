/** layui-v2.5.6 MIT License By https://www.layui.com */ ;
! function (e) {
    "use strict";
    var t = document,
        n = {
            modules: {},
            status: {},
            timeout: 10,
            event: {}
        },
        r = function () {
            this.v = "2.5.6"
        },
        o = function () {
            var e = t.currentScript ? t.currentScript.src : function () {
                for (var e, n = t.scripts, r = n.length - 1, o = r; o > 0; o--)
                    if ("interactive" === n[o].readyState) {
                        e = n[o].src;
                        break
                    } return e || n[r].src
            }();
            return e.substring(0, e.lastIndexOf("/") + 1)
        }(),
        a = function (t) {
            e.console && console.error && console.error("Layui hint: " + t)
        },
        i = "undefined" != typeof opera && "[object Opera]" === opera.toString(),
        u = {
            layer: "modules/layer",
            laydate: "modules/laydate",
            laypage: "modules/laypage",
            laytpl: "modules/laytpl",
            layim: "modules/layim",
            layedit: "modules/layedit",
            form: "modules/form",
            upload: "modules/upload",
            transfer: "modules/transfer",
            tree: "modules/tree",
            table: "modules/table",
            element: "modules/element",
            rate: "modules/rate",
            colorpicker: "modules/colorpicker",
            slider: "modules/slider",
            carousel: "modules/carousel",
            flow: "modules/flow",
            util: "modules/util",
            code: "modules/code",
            jquery: "modules/jquery",
            mobile: "modules/mobile",
            "layui.all": "../layui.all"
        };
    r.prototype.cache = n, r.prototype.define = function (e, t) {
        var r = this,
            o = "function" == typeof e,
            a = function () {
                var e = function (e, t) {
                    layui[e] = t, n.status[e] = !0
                };
                return "function" == typeof t && t(function (r, o) {
                    e(r, o), n.callback[r] = function () {
                        t(e)
                    }
                }), this
            };
        return o && (t = e, e = []), !layui["layui.all"] && layui["layui.mobile"] ? a.call(r) : (r.use(e, a), r)
    }, r.prototype.use = function (e, r, l) {
        function c(e, t) {
            var r = "PLaySTATION 3" === navigator.platform ? /^complete$/ : /^(complete|loaded)$/;
            ("load" === e.type || r.test((e.currentTarget || e.srcElement).readyState)) && (n.modules[d] = t, y.removeChild(h), function o() {
                return ++m > 1e3 * n.timeout / 4 ? a(d + " is not a valid module") : void(n.status[d] ? s() : setTimeout(o, 4))
            }())
        }

        function s() {
            l.push(layui[d]), e.length > 1 ? p.use(e.slice(1), r, l) : "function" == typeof r && r.apply(layui, l)
        }
        var p = this,
            f = n.dir = n.dir ? n.dir : o,
            y = t.getElementsByTagName("head")[0];
        e = "string" == typeof e ? [e] : e, window.jQuery && jQuery.fn.on && (p.each(e, function (t, n) {
            "jquery" === n && e.splice(t, 1)
        }), layui.jquery = layui.$ = jQuery);
        var d = e[0],
            m = 0;
        if (l = l || [], n.host = n.host || (f.match(/\/\/([\s\S]+?)\//) || ["//" + location.host + "/"])[0], 0 === e.length || layui["layui.all"] && u[d] || !layui["layui.all"] && layui["layui.mobile"] && u[d]) return s(), p;
        var v = (u[d] ? f + "lay/" : /^\{\/\}/.test(p.modules[d]) ? "" : n.base || "") + (p.modules[d] || d) + ".js";
        if (v = v.replace(/^\{\/\}/, ""), !n.modules[d] && layui[d] && (n.modules[d] = v), n.modules[d]) ! function g() {
            return ++m > 1e3 * n.timeout / 4 ? a(d + " is not a valid module") : void("string" == typeof n.modules[d] && n.status[d] ? s() : setTimeout(g, 4))
        }();
        else {
            var h = t.createElement("script");
            h.async = !0, h.charset = "utf-8", h.src = v + function () {
                var e = n.version === !0 ? n.v || (new Date).getTime() : n.version || "";
                return e ? "?v=" + e : ""
            }(), y.appendChild(h), !h.attachEvent || h.attachEvent.toString && h.attachEvent.toString().indexOf("[native code") < 0 || i ? h.addEventListener("load", function (e) {
                c(e, v)
            }, !1) : h.attachEvent("onreadystatechange", function (e) {
                c(e, v)
            }), n.modules[d] = v
        }
        return p
    }, r.prototype.getStyle = function (t, n) {
        var r = t.currentStyle ? t.currentStyle : e.getComputedStyle(t, null);
        return r[r.getPropertyValue ? "getPropertyValue" : "getAttribute"](n)
    }, r.prototype.link = function (e, r, o) {
        var i = this,
            u = t.createElement("link"),
            l = t.getElementsByTagName("head")[0];
        "string" == typeof r && (o = r);
        var c = (o || e).replace(/\.|\//g, ""),
            s = u.id = "layuicss-" + c,
            p = 0;
        return u.rel = "stylesheet", u.href = e + (n.debug ? "?v=" + (new Date).getTime() : ""), u.media = "all", t.getElementById(s) || l.appendChild(u), "function" != typeof r ? i : (function f() {
            return ++p > 1e3 * n.timeout / 100 ? a(e + " timeout") : void(1989 === parseInt(i.getStyle(t.getElementById(s), "width")) ? function () {
                r()
            }() : setTimeout(f, 100))
        }(), i)
    }, n.callback = {}, r.prototype.factory = function (e) {
        if (layui[e]) return "function" == typeof n.callback[e] ? n.callback[e] : null
    }, r.prototype.addcss = function (e, t, r) {
        return layui.link(n.dir + "css/" + e, t, r)
    }, r.prototype.img = function (e, t, n) {
        var r = new Image;
        return r.src = e, r.complete ? t(r) : (r.onload = function () {
            r.onload = null, "function" == typeof t && t(r)
        }, void(r.onerror = function (e) {
            r.onerror = null, "function" == typeof n && n(e)
        }))
    }, r.prototype.config = function (e) {
        e = e || {};
        for (var t in e) n[t] = e[t];
        return this
    }, r.prototype.modules = function () {
        var e = {};
        for (var t in u) e[t] = u[t];
        return e
    }(), r.prototype.extend = function (e) {
        var t = this;
        e = e || {};
        for (var n in e) t[n] || t.modules[n] ? a("模块名 " + n + " 已被占用") : t.modules[n] = e[n];
        return t
    }, r.prototype.router = function (e) {
        var t = this,
            e = e || location.hash,
            n = {
                path: [],
                search: {},
                hash: (e.match(/[^#](#.*$)/) || [])[1] || ""
            };
        return /^#\//.test(e) ? (e = e.replace(/^#\//, ""), n.href = "/" + e, e = e.replace(/([^#])(#.*$)/, "$1").split("/") || [], t.each(e, function (e, t) {
            /^\w+=/.test(t) ? function () {
                t = t.split("="), n.search[t[0]] = t[1]
            }() : n.path.push(t)
        }), n) : n
    }, r.prototype.url = function (e) {
        var t = this,
            n = {
                pathname: function () {
                    var t = e ? function () {
                        var t = (e.match(/\.[^.]+?\/.+/) || [])[0] || "";
                        return t.replace(/^[^\/]+/, "").replace(/\?.+/, "")
                    }() : location.pathname;
                    return t.replace(/^\//, "").split("/")
                }(),
                search: function () {
                    var n = {},
                        r = (e ? function () {
                            var t = (e.match(/\?.+/) || [])[0] || "";
                            return t.replace(/\#.+/, "")
                        }() : location.search).replace(/^\?+/, "").split("&");
                    return t.each(r, function (e, t) {
                        var r = t.indexOf("="),
                            o = function () {
                                return r < 0 ? t.substr(0, t.length) : 0 !== r && t.substr(0, r)
                            }();
                        o && (n[o] = r > 0 ? t.substr(r + 1) : null)
                    }), n
                }(),
                hash: t.router(function () {
                    return e ? (e.match(/#.+/) || [])[0] || "" : location.hash
                }())
            };
        return n
    }, r.prototype.data = function (t, n, r) {
        if (t = t || "layui", r = r || localStorage, e.JSON && e.JSON.parse) {
            if (null === n) return delete r[t];
            n = "object" == typeof n ? n : {
                key: n
            };
            try {
                var o = JSON.parse(r[t])
            } catch (a) {
                var o = {}
            }
            return "value" in n && (o[n.key] = n.value), n.remove && delete o[n.key], r[t] = JSON.stringify(o), n.key ? o[n.key] : o
        }
    }, r.prototype.sessionData = function (e, t) {
        return this.data(e, t, sessionStorage)
    }, r.prototype.device = function (t) {
        var n = navigator.userAgent.toLowerCase(),
            r = function (e) {
                var t = new RegExp(e + "/([^\\s\\_\\-]+)");
                return e = (n.match(t) || [])[1], e || !1
            },
            o = {
                os: function () {
                    return /windows/.test(n) ? "windows" : /linux/.test(n) ? "linux" : /iphone|ipod|ipad|ios/.test(n) ? "ios" : /mac/.test(n) ? "mac" : void 0
                }(),
                ie: function () {
                    return !!(e.ActiveXObject || "ActiveXObject" in e) && ((n.match(/msie\s(\d+)/) || [])[1] || "11")
                }(),
                weixin: r("micromessenger")
            };
        return t && !o[t] && (o[t] = r(t)), o.android = /android/.test(n), o.ios = "ios" === o.os, o.mobile = !(!o.android && !o.ios), o
    }, r.prototype.hint = function () {
        return {
            error: a
        }
    }, r.prototype.each = function (e, t) {
        var n, r = this;
        if ("function" != typeof t) return r;
        if (e = e || [], e.constructor === Object) {
            for (n in e)
                if (t.call(e[n], n, e[n])) break
        } else
            for (n = 0; n < e.length && !t.call(e[n], n, e[n]); n++);
        return r
    }, r.prototype.sort = function (e, t, n) {
        var r = JSON.parse(JSON.stringify(e || []));
        return t ? (r.sort(function (e, n) {
            var r = /^-?\d+$/,
                o = e[t],
                a = n[t];
            return r.test(o) && (o = parseFloat(o)), r.test(a) && (a = parseFloat(a)), o && !a ? 1 : !o && a ? -1 : o > a ? 1 : o < a ? -1 : 0
        }), n && r.reverse(), r) : r
    }, r.prototype.stope = function (t) {
        t = t || e.event;
        try {
            t.stopPropagation()
        } catch (n) {
            t.cancelBubble = !0
        }
    }, r.prototype.onevent = function (e, t, n) {
        return "string" != typeof e || "function" != typeof n ? this : r.event(e, t, null, n)
    }, r.prototype.event = r.event = function (e, t, r, o) {
        var a = this,
            i = null,
            u = t.match(/\((.*)\)$/) || [],
            l = (e + "." + t).replace(u[0], ""),
            c = u[1] || "",
            s = function (e, t) {
                var n = t && t.call(a, r);
                n === !1 && null === i && (i = !1)
            };
        return o ? (n.event[l] = n.event[l] || {}, n.event[l][c] = [o], this) : (layui.each(n.event[l], function (e, t) {
            return "{*}" === c ? void layui.each(t, s) : ("" === e && layui.each(t, s), void(c && e === c && layui.each(t, s)))
        }), i)
    }, e.layui = new r
}(window);