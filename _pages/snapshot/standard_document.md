---
permalink: "/snapshot/standard_documentation.html"
---
<html lang="en">
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Asciidoctor 1.5.7.1">
<meta name="description" content="A set of types and methods which can be used for the manipulation of geographic information structured following the specifications adopted by the Technical Committee 211 of the International Organization for Standardization (ISO) and by the Open Geospatial Consortium (OGC).">
<meta name="keywords" content="GeoAPI, programming, Java, Python, interface, geospatial, metadata, referencing, feature">
<title>OGC GeoAPI 3.1/4.0</title>
<style>
/*! normalize.css v2.1.2 | MIT License | git.io/normalize */
/* ========================================================================== HTML5 display definitions ========================================================================== */
/** Correct `block` display not defined in IE 8/9. */
article, aside, details, figcaption, figure, footer, header, hgroup, main, nav, section, summary {
    display: block;
}

/** Correct `inline-block` display not defined in IE 8/9. */
audio, canvas, video {
    display: inline-block;
}

/** Prevent modern browsers from displaying `audio` without controls. Remove excess height in iOS 5 devices. */
audio:not([controls]) {
    display: none;
    height: 0;
}

/** Address `[hidden]` styling not present in IE 8/9. Hide the `template` element in IE, Safari, and Firefox < 22. */
[hidden], template {
    display: none;
}

script {
    display: none !important;
}

/* ========================================================================== Base ========================================================================== */
/** 1. Set default font family to sans-serif. 2. Prevent iOS text size adjust after orientation change, without disabling user zoom. */
html {
    font-family: sans-serif; /* 1 */
    -ms-text-size-adjust: 100%; /* 2 */
    -webkit-text-size-adjust: 100%; /* 2 */
}

/** Remove default margin. */
body {
    margin: 0;
}

/* ========================================================================== Links ========================================================================== */
/** Remove the gray background color from active links in IE 10. */
a {
    background: transparent;
}

/** Address `outline` inconsistency between Chrome and other browsers. */
a:focus {
    outline: thin dotted;
}

/** Improve readability when focused and also mouse hovered in all browsers. */
a:active, a:hover {
    outline: 0;
}

/* ========================================================================== Typography ========================================================================== */
/** Address variable `h1` font-size and margin within `section` and `article` contexts in Firefox 4+, Safari 5, and Chrome. */
h1 {
    font-size: 2em;
    margin: 0.67em 0;
}

/** Address styling not present in IE 8/9, Safari 5, and Chrome. */
abbr[title] {
    border-bottom: 1px dotted;
}

/** Address style set to `bolder` in Firefox 4+, Safari 5, and Chrome. */
b, strong {
    font-weight: bold;
}

/** Address styling not present in Safari 5 and Chrome. */
dfn {
    font-style: italic;
}

/** Address differences between Firefox and other browsers. */
hr {
    -moz-box-sizing: content-box;
    box-sizing: content-box;
    height: 0;
}

/** Address styling not present in IE 8/9. */
mark {
    background: #ff0;
    color: #000;
}

/** Correct font family set oddly in Safari 5 and Chrome. */
code, kbd, pre, samp {
    font-family: monospace, serif;
    font-size: 1em;
}

/** Improve readability of pre-formatted text in all browsers. */
pre {
    white-space: pre-wrap;
}

/** Set consistent quote types. */
q {
    quotes: "\201C" "\201D" "\2018" "\2019";
}

/** Address inconsistent and variable font size in all browsers. */
small {
    font-size: 80%;
}

/** Address inconsistent and variable font size in all browsers. */
big {
    font-size: 160%;
}

/** Prevent `sub` and `sup` affecting `line-height` in all browsers. */
sub, sup {
    font-size: 75%;
    line-height: 0;
    position: relative;
    vertical-align: baseline;
}

sup {
    top: -0.5em;
}

sub {
    bottom: -0.25em;
}

/* ========================================================================== Embedded content ========================================================================== */
/** Remove border when inside `a` element in IE 8/9. */
img {
    border: 0;
}

/** Correct overflow displayed oddly in IE 9. */
svg:not(:root) {
    overflow: hidden;
}

/* ========================================================================== Figures ========================================================================== */
/** Address margin not present in IE 8/9 and Safari 5. */
figure {
    margin: 0;
}

/* ========================================================================== Forms ========================================================================== */
/** Define consistent border, margin, and padding. */
fieldset {
    border: 1px solid #c0c0c0;
    margin: 0 2px;
    padding: 0.35em 0.625em 0.75em;
}

/** 1. Correct `color` not being inherited in IE 8/9. 2. Remove padding so people aren't caught out if they zero out fieldsets. */
legend {
    border: 0; /* 1 */
    padding: 0; /* 2 */
}

/** 1. Correct font family not being inherited in all browsers. 2. Correct font size not being inherited in all browsers. 3. Address margins set differently in Firefox 4+, Safari 5, and Chrome. */
button, input, select, textarea {
    font-family: inherit; /* 1 */
    font-size: 100%; /* 2 */
    margin: 0; /* 3 */
}

/** Address Firefox 4+ setting `line-height` on `input` using `!important` in the UA stylesheet. */
button, input {
    line-height: normal;
}

/** Address inconsistent `text-transform` inheritance for `button` and `select`. All other form control elements do not inherit `text-transform` values. Correct `button` style inheritance in Chrome, Safari 5+, and IE 8+. Correct `select` style inheritance in Firefox 4+ and Opera. */
button, select {
    text-transform: none;
}

/** 1. Avoid the WebKit bug in Android 4.0.* where (2) destroys native `audio` and `video` controls. 2. Correct inability to style clickable `input` types in iOS. 3. Improve usability and consistency of cursor style between image-type `input` and others. */
button, html input[type="button"], input[type="reset"], input[type="submit"] {
    -webkit-appearance: button; /* 2 */
    cursor: pointer; /* 3 */
}

/** Re-set default cursor for disabled elements. */
button[disabled], html input[disabled] {
    cursor: default;
}

/** 1. Address box sizing set to `content-box` in IE 8/9. 2. Remove excess padding in IE 8/9. */
input[type="checkbox"], input[type="radio"] {
    box-sizing: border-box; /* 1 */
    padding: 0; /* 2 */
}

/** 1. Address `appearance` set to `searchfield` in Safari 5 and Chrome. 2. Address `box-sizing` set to `border-box` in Safari 5 and Chrome (include `-moz` to future-proof). */
input[type="search"] {
    -webkit-appearance: textfield; /* 1 */
    -moz-box-sizing: content-box;
    -webkit-box-sizing: content-box; /* 2 */
    box-sizing: content-box;
}

/** Remove inner padding and search cancel button in Safari 5 and Chrome on OS X. */
input[type="search"]::-webkit-search-cancel-button, input[type="search"]::-webkit-search-decoration {
    -webkit-appearance: none;
}

/** Remove inner padding and border in Firefox 4+. */
button::-moz-focus-inner, input::-moz-focus-inner {
    border: 0;
    padding: 0;
}

/** 1. Remove default vertical scrollbar in IE 8/9. 2. Improve readability and alignment in all browsers. */
textarea {
    overflow: auto; /* 1 */
    vertical-align: top; /* 2 */
}

/* ========================================================================== Tables ========================================================================== */
/** Remove most spacing between table cells. */
table {
    border-collapse: collapse;
    border-spacing: 0;
}

meta.foundation-mq-small {
    font-family: "only screen and (min-width: 768px)";
    width: 768px;
}

meta.foundation-mq-medium {
    font-family: "only screen and (min-width:1280px)";
    width: 1280px;
}

meta.foundation-mq-large {
    font-family: "only screen and (min-width:1440px)";
    width: 1440px;
}

*, *:before, *:after {
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

html, body {
    font-size: 100%;
}

body {
    background: white;
    color: rgba(0, 0, 0, 0.8);
    padding: 0;
    margin: 0;
    font-family: "Noto Serif", "DejaVu Serif", serif;
    font-weight: normal;
    font-style: normal;
    line-height: 1;
    position: relative;
    cursor: auto;
}

a:hover {
    cursor: pointer;
}

img, object, embed {
    max-width: 100%;
    height: auto;
}

object, embed {
    height: 100%;
}

img {
    -ms-interpolation-mode: bicubic;
}

#map_canvas img, #map_canvas embed, #map_canvas object, .map_canvas img, .map_canvas embed, .map_canvas object {
    max-width: none !important;
}

.left {
    float: left !important;
}

.right {
    float: right !important;
}

.text-left {
    text-align: left !important;
}

.text-right {
    text-align: right !important;
}

.text-center {
    text-align: center !important;
}

.text-justify {
    text-align: justify !important;
}

.hide {
    display: none;
}

.antialiased, body {
    -webkit-font-smoothing: antialiased;
}

img {
    display: inline-block;
    vertical-align: middle;
}

textarea {
    height: auto;
    min-height: 50px;
}

select {
    width: 100%;
}

p.lead, .paragraph.lead > p, #preamble > .sectionbody > .paragraph:first-of-type p {
    font-size: 1.21875em;
    line-height: 1.6;
}

.subheader, .admonitionblock td.content > .title, .audioblock > .title, .exampleblock > .title, .imageblock > .title, .listingblock > .title, .literalblock > .title, .stemblock > .title, .openblock > .title, .paragraph > .title, .quoteblock > .title, table.tableblock > .title, .verseblock > .title, .videoblock > .title, .dlist > .title, .olist > .title, .ulist > .title, .qlist > .title, .hdlist > .title {
    line-height: 1.45;
    color: #000000;
    font-weight: normal;
    margin-top: 0;
    margin-bottom: 0.25em;
}

/* Typography resets */
div, dl, dt, dd, ul, ol, li, h1, h2, h3, #toctitle, .sidebarblock > .content > .title, h4, h5, h6, pre, form, p, blockquote, th, td {
    margin: 0;
    padding: 0;
    direction: ltr;
}

/* Default Link Styles */
a {
    color: #2156a5;
    text-decoration: underline;
    line-height: inherit;
}

a:hover, a:focus {
    color: #1d4b8f;
}

a img {
    border: none;
}

/* Default paragraph styles */
p {
    font-family: inherit;
    font-weight: normal;
    font-size: 1em;
    line-height: 1.6;
    margin-bottom: 1.25em;
    text-rendering: optimizeLegibility;
}

p aside {
    font-size: 0.875em;
    line-height: 1.35;
    font-style: italic;
}

/* Default header styles */
h1, h2, h3, #toctitle, .sidebarblock > .content > .title, h4, h5, h6 {
    font-family: "Noto Serif", "DejaVu Serif", serif;
    font-weight: 300;
    font-style: normal;
    color: #000000;
    text-rendering: optimizeLegibility;
    margin-top: 1em;
    margin-bottom: 0.5em;
    line-height: 1.0125em;
}

h1 small, h2 small, h3 small, #toctitle small, .sidebarblock > .content > .title small, h4 small, h5 small, h6 small {
    font-size: 60%;
    color: #000000;
    line-height: 0;
}

h1 {
    font-size: 160%;
}

h2 {
    font-size: 160%;
}

h3, #toctitle, .sidebarblock > .content > .title {
    font-size: 140%;
}

h4 {
    font-size: 1em;
}

h5 {
    font-size: 1em;
}

h6 {
    font-size: 1em;
}

hr {
    border: solid #ddddd8;
    border-width: 1px 0 0;
    clear: both;
    margin: 1.25em 0 1.1875em;
    height: 0;
}

/* Helpful Typography Defaults */
em, i {
    font-style: italic;
    line-height: inherit;
}

strong, b {
    font-weight: bold;
    line-height: inherit;
}

small {
    font-size: 60%;
    line-height: inherit;
}

code {
    font-family: "Droid Sans Mono", "DejaVu Sans Mono", "Monospace", monospace;
    font-weight: normal;
    color: rgba(0, 0, 0, 0.9);
}

/* Lists */
ul, ol, dl {
    font-size: 1em;
    line-height: 1.6;
    margin-bottom: 1.25em;
    list-style-position: outside;
    font-family: inherit;
}

ul, ol {
    margin-left: 1.5em;
}

ul.no-bullet, ol.no-bullet {
    margin-left: 1.5em;
}

/* Unordered Lists */
ul li ul, ul li ol {
    margin-left: 1.25em;
    margin-bottom: 0;
    font-size: 1em; /* Override nested font-size change */
}

ul.square li ul, ul.circle li ul, ul.disc li ul {
    list-style: inherit;
}

ul.square {
    list-style-type: square;
}

ul.circle {
    list-style-type: circle;
}

ul.disc {
    list-style-type: disc;
}

ul.no-bullet {
    list-style: none;
}

/* Ordered Lists */
ol li ul, ol li ol {
    margin-left: 1.25em;
    margin-bottom: 0;
}

/* Definition Lists */
dl dt {
    margin-bottom: 0.3125em;
    font-weight: bold;
}

dl dd {
    margin-bottom: 1.25em;
}

/* Abbreviations */
abbr, acronym {
    text-transform: uppercase;
    font-size: 90%;
    color: rgba(0, 0, 0, 0.8);
    border-bottom: 1px dotted #dddddd;
    cursor: help;
}

abbr {
    text-transform: none;
}

/* Blockquotes */
blockquote {
    margin: 0 0 1.25em;
    padding: 0.5625em 1.25em 0 1.1875em;
    border-left: 1px solid #dddddd;
}

blockquote cite {
    display: block;
    font-size: 0.9375em;
    color: rgba(0, 0, 0, 0.6);
}

blockquote cite:before {
    content: "\2014 \0020";
}

blockquote cite a, blockquote cite a:visited {
    color: rgba(0, 0, 0, 0.6);
}

blockquote, blockquote p {
    line-height: 1.6;
    color: rgba(0, 0, 0, 0.85);
}

/* Microformats */
.vcard {
    display: inline-block;
    margin: 0 0 1.25em 0;
    border: 1px solid #dddddd;
    padding: 0.625em 0.75em;
}

.vcard li {
    margin: 0;
    display: block;
}

.vcard .fn {
    font-weight: bold;
    font-size: 0.9375em;
}

.vevent .summary {
    font-weight: bold;
}

.vevent abbr {
    cursor: auto;
    text-decoration: none;
    font-weight: bold;
    border: none;
    padding: 0 0.0625em;
}

@media only screen and (min-width: 768px) {
    h1, h2, h3, #toctitle, .sidebarblock > .content > .title, h4, h5, h6 {
        font-family: "Noto Serif", "DejaVu Serif", serif;
        line-height: 1.2;
        font-weight: 300;
        font-style: normal;
        color: #000000;
        text-rendering: optimizeLegibility;
        margin-top: 1em;
        margin-bottom: 0.5em;
        line-height: 1.0125em;
    }

    h1 {
        font-size: 160%;
        font-weight: bold;
    }

    h2 {
        font-size: 160%;
        font-weight: bold;
    }

    h3, #toctitle, .sidebarblock > .content > .title {
        font-size: 140%;
        font-weight: bold;
    }

    h4 {
        font-size: 1em;
    }
}

/* Tables */
table {
    background: white;
    margin-bottom: 1.25em;
    border: solid 1px #dedede;
}

table thead, table tfoot {
    background: #f7f8f7;
    font-weight: bold;
}

table thead tr th, table thead tr td, table tfoot tr th, table tfoot tr td {
    padding: 0.5em 0.625em 0.625em;
    font-size: inherit;
    color: rgba(0, 0, 0, 0.8);
    text-align: left;
}

table tr th, table tr td {
    padding: 0.5625em 0.625em;
    font-size: inherit;
    color: rgba(0, 0, 0, 0.8);
}

table tr.even, table tr.alt, table tr:nth-of-type(even) {
    background: #f8f8f7;
}

table thead tr th, table tfoot tr th, table tbody tr td, table tr td, table tfoot tr td {
    display: table-cell;
    line-height: 1.6;
}

h1, h2, h3, #toctitle, .sidebarblock > .content > .title, h4, h5, h6 {
    line-height: 1.2;
    word-spacing: -0.05em;
}

h1 strong, h2 strong, h3 strong, #toctitle strong, .sidebarblock > .content > .title strong, h4 strong, h5 strong, h6 strong {
    font-weight: 400;
}

.clearfix:before, .clearfix:after, .float-group:before, .float-group:after {
    content: " ";
    display: table;
}

.clearfix:after, .float-group:after {
    clear: both;
}

*:not(pre) > code {
    font-size: 0.9375em;
    font-style: normal !important;
    letter-spacing: 0;
    padding: 0.1em 0.5ex;
    word-spacing: -0.15em;
    background-color: #f7f7f8;
    -webkit-border-radius: 4px;
    border-radius: 4px;
    line-height: 1.45;
    text-rendering: optimizeSpeed;
}

pre, pre > code {
    line-height: 1.45;
    color: rgba(0, 0, 0, 0.9);
    font-family: "Droid Sans Mono", "DejaVu Sans Mono", "Monospace", monospace;
    font-weight: normal;
    text-rendering: optimizeSpeed;
}

.keyseq {
    color: rgba(51, 51, 51, 0.8);
}

kbd {
    display: inline-block;
    color: rgba(0, 0, 0, 0.8);
    font-size: 0.75em;
    line-height: 1.4;
    background-color: #f7f7f7;
    border: 1px solid #ccc;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    -webkit-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.2), 0 0 0 0.1em white inset;
    box-shadow: 0 1px 0 rgba(0, 0, 0, 0.2), 0 0 0 0.1em white inset;
    margin: -0.15em 0.15em 0 0.15em;
    padding: 0.2em 0.6em 0.2em 0.5em;
    vertical-align: middle;
    white-space: nowrap;
}

.keyseq kbd:first-child {
    margin-left: 0;
}

.keyseq kbd:last-child {
    margin-right: 0;
}

.menuseq, .menu {
    color: rgba(0, 0, 0, 0.8);
}

b.button:before, b.button:after {
    position: relative;
    top: -1px;
    font-weight: normal;
}

b.button:before {
    content: "[";
    padding: 0 3px 0 2px;
}

b.button:after {
    content: "]";
    padding: 0 2px 0 3px;
}

p a > code:hover {
    color: rgba(0, 0, 0, 0.9);
}

#header, #content, #footnotes, #footer {
    width: 100%;
    margin-left: auto;
    margin-right: auto;
    margin-top: 0;
    margin-bottom: 0;
    max-width: 62.5em;
    *zoom: 1;
    position: relative;
    padding-left: 0.9375em;
    padding-right: 0.9375em;
}

#header:before, #header:after, #content:before, #content:after, #footnotes:before, #footnotes:after, #footer:before, #footer:after {
    content: " ";
    display: table;
}

#header:after, #content:after, #footnotes:after, #footer:after {
    clear: both;
}

#content {
    margin-top: 1.25em;
}

#content:before {
    content: none;
}

#header > h1:first-child {
    color: rgba(0, 0, 0, 0.85);
    margin-top: 2.25rem;
    margin-bottom: 0;
}

#header > h1:first-child + #toc {
    margin-top: 8px;
    border-top: 1px solid #ddddd8;
}

#header > h1:only-child, body.toc2 #header > h1:nth-last-child(2) {
    border-bottom: 1px solid #ddddd8;
    padding-bottom: 8px;
}

#header .details {
    border-bottom: 1px solid #ddddd8;
    line-height: 1.45;
    padding-top: 0.25em;
    padding-bottom: 0.25em;
    padding-left: 0.25em;
    color: rgba(0, 0, 0, 0.6);
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    -ms-flex-flow: row wrap;
    -webkit-flex-flow: row wrap;
    flex-flow: row wrap;
}

#header .details span:first-child {
    margin-left: -0.125em;
}

#header .details span.email a {
    color: rgba(0, 0, 0, 0.85);
}

#header .details br {
    display: none;
}

#header .details br + span:before {
    content: "\00a0\2013\00a0";
}

#header .details br + span.author:before {
    content: "\00a0\22c5\00a0";
    color: rgba(0, 0, 0, 0.85);
}

#header .details br + span#revremark:before {
    content: "\00a0|\00a0";
}

#header #revnumber {
    text-transform: capitalize;
}

#header #revnumber:after {
    content: "\00a0";
}

#content > h1:first-child:not([class]) {
    color: rgba(0, 0, 0, 0.85);
    border-bottom: 1px solid #ddddd8;
    padding-bottom: 8px;
    margin-top: 0;
    padding-top: 1rem;
    margin-bottom: 1.25rem;
}

#toc {
    border-bottom: 1px solid #efefed;
    padding-bottom: 0.5em;
}

#toc > ul {
    margin-left: 0.125em;
}

#toc ul.sectlevel0 > li > a {
    font-style: italic;
}

#toc ul.sectlevel0 ul.sectlevel1 {
    margin: 0.5em 0;
}

#toc ul {
    font-family: "Open Sans", "DejaVu Sans", sans-serif;
    list-style-type: none;
}

#toc a {
    text-decoration: none;
}

#toc a:active {
    text-decoration: underline;
}

#toctitle {
    color: #7a2518;
    font-size: 1.2em;
}

@media only screen and (min-width: 768px) {
    #toctitle {
        font-size: 1.375em;
    }

    body.toc2 {
        padding-left: 15em;
        padding-right: 0;
    }

    #toc.toc2 {
        margin-top: 0 !important;
        background-color: #f8f8f7;
        position: fixed;
        width: 15em;
        left: 0;
        top: 0;
        border-right: 1px solid #efefed;
        border-top-width: 0 !important;
        border-bottom-width: 0 !important;
        z-index: 1000;
        padding: 1.25em 1em;
        height: 100%;
        overflow: auto;
    }

    #toc.toc2 #toctitle {
        margin-top: 0;
        font-size: 1.2em;
    }

    #toc.toc2 > ul {
        font-size: 0.9em;
        margin-bottom: 0;
    }

    #toc.toc2 ul ul {
        margin-left: 0;
        padding-left: 1em;
    }

    #toc.toc2 ul.sectlevel0 ul.sectlevel1 {
        padding-left: 0;
        margin-top: 0.5em;
        margin-bottom: 0.5em;
    }

    body.toc2.toc-right {
        padding-left: 0;
        padding-right: 15em;
    }

    body.toc2.toc-right #toc.toc2 {
        border-right-width: 0;
        border-left: 1px solid #efefed;
        left: auto;
        right: 0;
    }
}

@media only screen and (min-width: 1280px) {
    body.toc2 {
        padding-left: 20em;
        padding-right: 0;
    }

    #toc.toc2 {
        width: 20em;
    }

    #toc.toc2 #toctitle {
        font-size: 1.375em;
    }

    #toc.toc2 > ul {
        font-size: 0.95em;
    }

    #toc.toc2 ul ul {
        padding-left: 1.25em;
    }

    body.toc2.toc-right {
        padding-left: 0;
        padding-right: 20em;
    }
}

#content #toc {
    border-style: solid;
    border-width: 1px;
    border-color: #e0e0dc;
    margin-bottom: 1.25em;
    padding: 1.25em;
    background: #f8f8f7;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}

#content #toc > :first-child {
    margin-top: 0;
}

#content #toc > :last-child {
    margin-bottom: 0;
}

#footer {
    max-width: 100%;
    background-color: rgba(0, 0, 0, 0.8);
    padding: 1.25em;
}

#footer-text {
    color: rgba(255, 255, 255, 0.8);
    line-height: 1.44;
}

.sect1 {
    padding-bottom: 0.625em;
}

@media only screen and (min-width: 768px) {
    .sect1 {
        padding-bottom: 1.25em;
    }
}

.sect1 + .sect1 {
    border-top: 1px solid #efefed;
}

#content h1 > a.anchor, h2 > a.anchor, h3 > a.anchor, #toctitle > a.anchor, .sidebarblock > .content > .title > a.anchor, h4 > a.anchor, h5 > a.anchor, h6 > a.anchor {
    position: absolute;
    z-index: 1001;
    width: 1.5ex;
    margin-left: -1.5ex;
    display: block;
    text-decoration: none !important;
    visibility: hidden;
    text-align: center;
    font-weight: normal;
}

#content h1 > a.anchor:before, h2 > a.anchor:before, h3 > a.anchor:before, #toctitle > a.anchor:before, .sidebarblock > .content > .title > a.anchor:before, h4 > a.anchor:before, h5 > a.anchor:before, h6 > a.anchor:before {
    content: "\00A7";
    font-size: 0.85em;
    display: block;
    padding-top: 0.1em;
}

#content h1:hover > a.anchor, #content h1 > a.anchor:hover, h2:hover > a.anchor, h2 > a.anchor:hover, h3:hover > a.anchor, #toctitle:hover > a.anchor, .sidebarblock > .content > .title:hover > a.anchor, h3 > a.anchor:hover, #toctitle > a.anchor:hover, .sidebarblock > .content > .title > a.anchor:hover, h4:hover > a.anchor, h4 > a.anchor:hover, h5:hover > a.anchor, h5 > a.anchor:hover, h6:hover > a.anchor, h6 > a.anchor:hover {
    visibility: visible;
}

#content h1 > a.link, h2 > a.link, h3 > a.link, #toctitle > a.link, .sidebarblock > .content > .title > a.link, h4 > a.link, h5 > a.link, h6 > a.link {
    color: #ba3925;
    text-decoration: none;
}

#content h1 > a.link:hover, h2 > a.link:hover, h3 > a.link:hover, #toctitle > a.link:hover, .sidebarblock > .content > .title > a.link:hover, h4 > a.link:hover, h5 > a.link:hover, h6 > a.link:hover {
    color: #a53221;
}

.audioblock, .imageblock, .literalblock, .listingblock, .stemblock, .videoblock {
    margin-bottom: 1.25em;
}

.admonitionblock td.content > .title, .audioblock > .title, .exampleblock > .title, .imageblock > .title, .listingblock > .title, .literalblock > .title, .stemblock > .title, .openblock > .title, .paragraph > .title, .quoteblock > .title, table.tableblock > .title, .verseblock > .title, .videoblock > .title, .dlist > .title, .olist > .title, .ulist > .title, .qlist > .title, .hdlist > .title {
    text-rendering: optimizeLegibility;
    text-align: left;
    font-family: "Noto Serif", "DejaVu Serif", serif;
    font-size: 1rem;
    font-style: italic;
}

table.tableblock > caption.title {
    white-space: nowrap;
    overflow: visible;
    max-width: 0;
}

.paragraph.lead > p, #preamble > .sectionbody > .paragraph:first-of-type p {
    color: rgba(0, 0, 0, 0.85);
}

table.tableblock #preamble > .sectionbody > .paragraph:first-of-type p {
    font-size: inherit;
}

.admonitionblock > table {
    border-collapse: separate;
    border: 0;
    background: none;
    width: 100%;
}

.admonitionblock > table td.icon {
    text-align: center;
    width: 80px;
}

.admonitionblock > table td.icon img {
    max-width: none;
}

.admonitionblock > table td.icon .title {
    font-weight: bold;
    font-family: "Open Sans", "DejaVu Sans", sans-serif;
    text-transform: uppercase;
}

.admonitionblock > table td.content {
    padding-left: 1.125em;
    padding-right: 1.25em;
    border-left: 1px solid #ddddd8;
    color: rgba(0, 0, 0, 0.6);
}

.admonitionblock > table td.content > :last-child > :last-child {
    margin-bottom: 0;
}

.exampleblock > .content {
    border-style: solid;
    border-width: 1px;
    border-color: #e6e6e6;
    margin-bottom: 1.25em;
    padding: 1.25em;
    background: white;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}

.exampleblock > .content > :first-child {
    margin-top: 0;
}

.exampleblock > .content > :last-child {
    margin-bottom: 0;
}

.sidebarblock {
    border-style: solid;
    border-width: 1px;
    border-color: #e0e0dc;
    margin-bottom: 1.25em;
    padding: 1.25em;
    background: #f8f8f7;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}

.sidebarblock > :first-child {
    margin-top: 0;
}

.sidebarblock > :last-child {
    margin-bottom: 0;
}

.sidebarblock > .content > .title {
    color: #7a2518;
    margin-top: 0;
    text-align: center;
}

.exampleblock > .content > :last-child > :last-child, .exampleblock > .content .olist > ol > li:last-child > :last-child, .exampleblock > .content .ulist > ul > li:last-child > :last-child, .exampleblock > .content .qlist > ol > li:last-child > :last-child, .sidebarblock > .content > :last-child > :last-child, .sidebarblock > .content .olist > ol > li:last-child > :last-child, .sidebarblock > .content .ulist > ul > li:last-child > :last-child, .sidebarblock > .content .qlist > ol > li:last-child > :last-child {
    margin-bottom: 0;
}

.literalblock pre, .listingblock pre:not(.highlight), .listingblock pre[class="highlight"], .listingblock pre[class^="highlight "], .listingblock pre.CodeRay, .listingblock pre.prettyprint {
    background: #f7f7f8;
}

.sidebarblock .literalblock pre, .sidebarblock .listingblock pre:not(.highlight), .sidebarblock .listingblock pre[class="highlight"], .sidebarblock .listingblock pre[class^="highlight "], .sidebarblock .listingblock pre.CodeRay, .sidebarblock .listingblock pre.prettyprint {
    background: #f2f1f1;
}

.literalblock pre, .literalblock pre[class], .listingblock pre, .listingblock pre[class] {
    -webkit-border-radius: 4px;
    border-radius: 4px;
    word-wrap: break-word;
    padding: 1em;
    font-size: 0.8125em;
}

.literalblock pre.nowrap, .literalblock pre[class].nowrap, .listingblock pre.nowrap, .listingblock pre[class].nowrap {
    overflow-x: auto;
    white-space: pre;
    word-wrap: normal;
}

@media only screen and (min-width: 768px) {
    .literalblock pre, .literalblock pre[class], .listingblock pre, .listingblock pre[class] {
        font-size: 0.90625em;
    }
}

@media only screen and (min-width: 1280px) {
    .literalblock pre, .literalblock pre[class], .listingblock pre, .listingblock pre[class] {
        font-size: 1em;
    }
}

.literalblock.output pre {
    color: #f7f7f8;
    background-color: rgba(0, 0, 0, 0.9);
}

.listingblock pre.highlightjs {
    padding: 0;
}

.listingblock pre.highlightjs > code {
    padding: 1em;
    -webkit-border-radius: 4px;
    border-radius: 4px;
}

.listingblock pre.prettyprint {
    border-width: 0;
}

.listingblock > .content {
    position: relative;
}

.listingblock code[data-lang]:before {
    display: none;
    content: attr(data-lang);
    position: absolute;
    font-size: 0.75em;
    top: 0.425rem;
    right: 0.5rem;
    line-height: 1;
    text-transform: uppercase;
    color: #999;
}

.listingblock:hover code[data-lang]:before {
    display: block;
}

.listingblock.terminal pre .command:before {
    content: attr(data-prompt);
    padding-right: 0.5em;
    color: #999;
}

.listingblock.terminal pre .command:not([data-prompt]):before {
    content: "$";
}

table.pyhltable {
    border-collapse: separate;
    border: 0;
    margin-bottom: 0;
    background: none;
}

table.pyhltable td {
    vertical-align: top;
    padding-top: 0;
    padding-bottom: 0;
}

table.pyhltable td.code {
    padding-left: .75em;
    padding-right: 0;
}

pre.pygments .lineno, table.pyhltable td:not(.code) {
    color: #999;
    padding-left: 0;
    padding-right: .5em;
    border-right: 1px solid #ddddd8;
}

pre.pygments .lineno {
    display: inline-block;
    margin-right: .25em;
}

table.pyhltable .linenodiv {
    background: none !important;
    padding-right: 0 !important;
}

.quoteblock {
    margin: 0 1em 1.25em 1.5em;
    display: table;
}

.quoteblock > .title {
    margin-left: -1.5em;
    margin-bottom: 0.75em;
}

.quoteblock blockquote, .quoteblock blockquote p {
    color: rgba(0, 0, 0, 0.85);
    font-size: 1.15rem;
    line-height: 1.75;
    word-spacing: 0.1em;
    letter-spacing: 0;
    font-style: italic;
    text-align: justify;
}

.quoteblock blockquote {
    margin: 0;
    padding: 0;
    border: 0;
}

.quoteblock blockquote:before {
    content: "\201c";
    float: left;
    font-size: 2.75em;
    font-weight: bold;
    line-height: 0.6em;
    margin-left: -0.6em;
    color: #7a2518;
    text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.quoteblock blockquote > .paragraph:last-child p {
    margin-bottom: 0;
}

.quoteblock .attribution {
    margin-top: 0.5em;
    margin-right: 0.5ex;
    text-align: right;
}

.quoteblock .quoteblock {
    margin-left: 0;
    margin-right: 0;
    padding: 0.5em 0;
    border-left: 3px solid rgba(0, 0, 0, 0.6);
}

.quoteblock .quoteblock blockquote {
    padding: 0 0 0 0.75em;
}

.quoteblock .quoteblock blockquote:before {
    display: none;
}

.verseblock {
    margin: 0 1em 1.25em 1em;
}

.verseblock pre {
    font-family: "Open Sans", "DejaVu Sans", sans;
    font-size: 1.15rem;
    color: rgba(0, 0, 0, 0.85);
    font-weight: 300;
    text-rendering: optimizeLegibility;
}

.verseblock pre strong {
    font-weight: 400;
}

.verseblock .attribution {
    margin-top: 1.25rem;
    margin-left: 0.5ex;
}

.quoteblock .attribution, .verseblock .attribution {
    font-size: 0.9375em;
    line-height: 1.45;
    font-style: italic;
}

.quoteblock .attribution br, .verseblock .attribution br {
    display: none;
}

.quoteblock .attribution cite, .verseblock .attribution cite {
    display: block;
    letter-spacing: -0.05em;
    color: rgba(0, 0, 0, 0.6);
}

.quoteblock.abstract {
    margin: 0 0 1.25em 0;
    display: block;
}

.quoteblock.abstract blockquote, .quoteblock.abstract blockquote p {
    text-align: left;
    word-spacing: 0;
}

.quoteblock.abstract blockquote:before, .quoteblock.abstract blockquote p:first-of-type:before {
    display: none;
}

table.tableblock {
    max-width: 100%;
    border-collapse: separate;
}

table.tableblock td > .paragraph:last-child p > p:last-child, table.tableblock th > p:last-child, table.tableblock td > p:last-child {
    margin-bottom: 0;
}

table.spread {
    width: 100%;
}

table.tableblock, th.tableblock, td.tableblock {
    border: 0 solid #dedede;
}

table.grid-all th.tableblock, table.grid-all td.tableblock {
    border-width: 0 1px 1px 0;
}

table.grid-all tfoot > tr > th.tableblock, table.grid-all tfoot > tr > td.tableblock {
    border-width: 1px 1px 0 0;
}

table.grid-cols th.tableblock, table.grid-cols td.tableblock {
    border-width: 0 1px 0 0;
}

table.grid-all * > tr > .tableblock:last-child, table.grid-cols * > tr > .tableblock:last-child {
    border-right-width: 0;
}

table.grid-rows th.tableblock, table.grid-rows td.tableblock {
    border-width: 0 0 1px 0;
}

table.grid-all tbody > tr:last-child > th.tableblock, table.grid-all tbody > tr:last-child > td.tableblock, table.grid-all thead:last-child > tr > th.tableblock, table.grid-rows tbody > tr:last-child > th.tableblock, table.grid-rows tbody > tr:last-child > td.tableblock, table.grid-rows thead:last-child > tr > th.tableblock {
    border-bottom-width: 0;
}

table.grid-rows tfoot > tr > th.tableblock, table.grid-rows tfoot > tr > td.tableblock {
    border-width: 1px 0 0 0;
}

table.frame-all {
    border-width: 1px;
}

table.frame-sides {
    border-width: 0 1px;
}

table.frame-topbot {
    border-width: 1px 0;
}

th.halign-left, td.halign-left {
    text-align: left;
}

th.halign-right, td.halign-right {
    text-align: right;
}

th.halign-center, td.halign-center {
    text-align: center;
}

th.valign-top, td.valign-top {
    vertical-align: top;
}

th.valign-bottom, td.valign-bottom {
    vertical-align: bottom;
}

th.valign-middle, td.valign-middle {
    vertical-align: middle;
}

table thead th, table tfoot th {
    font-weight: bold;
}

tbody tr th {
    display: table-cell;
    line-height: 1.6;
    background: #f7f8f7;
}

tbody tr th, tbody tr th p, tfoot tr th, tfoot tr th p {
    color: rgba(0, 0, 0, 0.8);
    font-weight: bold;
}

p.tableblock > code:only-child {
    background: none;
    padding: 0;
}

p.tableblock {
    font-size: 1em;
}

td > div.verse {
    white-space: pre;
}

ol {
    margin-left: 1.75em;
}

ul li ol {
    margin-left: 1.5em;
}

dl dd {
    margin-left: 1.125em;
}

dl dd:last-child, dl dd:last-child > :last-child {
    margin-bottom: 0;
}

ol > li p, ul > li p, ul dd, ol dd, .olist .olist, .ulist .ulist, .ulist .olist, .olist .ulist {
    margin-bottom: 0.625em;
}

ul.unstyled, ol.unnumbered, ul.checklist, ul.none {
    list-style-type: none;
}

ul.unstyled, ol.unnumbered, ul.checklist {
    margin-left: 0.625em;
}

ul.checklist li > p:first-child > .fa-check-square-o:first-child, ul.checklist li > p:first-child > input[type="checkbox"]:first-child {
    margin-right: 0.25em;
}

ul.checklist li > p:first-child > input[type="checkbox"]:first-child {
    position: relative;
    top: 1px;
}

ul.inline {
    margin: 0 auto 0.625em auto;
    margin-left: -1.375em;
    margin-right: 0;
    padding: 0;
    list-style: none;
    overflow: hidden;
}

ul.inline > li {
    list-style: none;
    float: left;
    margin-left: 1.375em;
    display: block;
}

ul.inline > li > * {
    display: block;
}

.unstyled dl dt {
    font-weight: normal;
    font-style: normal;
}

ol.arabic {
    list-style-type: decimal;
}

ol.decimal {
    list-style-type: decimal-leading-zero;
}

ol.loweralpha {
    list-style-type: lower-alpha;
}

ol.upperalpha {
    list-style-type: upper-alpha;
}

ol.lowerroman {
    list-style-type: lower-roman;
}

ol.upperroman {
    list-style-type: upper-roman;
}

ol.lowergreek {
    list-style-type: lower-greek;
}

.hdlist > table, .colist > table {
    border: 0;
    background: none;
}

.hdlist > table > tbody > tr, .colist > table > tbody > tr {
    background: none;
}

td.hdlist1 {
    padding-right: .75em;
    font-weight: bold;
}

td.hdlist1, td.hdlist2 {
    vertical-align: top;
}

.literalblock + .colist, .listingblock + .colist {
    margin-top: -0.5em;
}

.colist > table tr > td:first-of-type {
    padding: 0 0.75em;
    line-height: 1;
}

.colist > table tr > td:last-of-type {
    padding: 0.25em 0;
}

.thumb, .th {
    line-height: 0;
    display: inline-block;
    border: solid 4px white;
    -webkit-box-shadow: 0 0 0 1px #dddddd;
    box-shadow: 0 0 0 1px #dddddd;
}

.imageblock.left, .imageblock[style*="float: left"] {
    margin: 0.25em 0.625em 1.25em 0;
}

.imageblock.right, .imageblock[style*="float: right"] {
    margin: 0.25em 0 1.25em 0.625em;
}

.imageblock > .title {
    margin-bottom: 0;
}

.imageblock.thumb, .imageblock.th {
    border-width: 6px;
}

.imageblock.thumb > .title, .imageblock.th > .title {
    padding: 0 0.125em;
}

.image.left, .image.right {
    margin-top: 0.25em;
    margin-bottom: 0.25em;
    display: inline-block;
    line-height: 0;
}

.image.left {
    margin-right: 0.625em;
}

.image.right {
    margin-left: 0.625em;
}

a.image {
    text-decoration: none;
}

span.footnote, span.footnoteref {
    vertical-align: super;
    font-size: 0.875em;
}

span.footnote a, span.footnoteref a {
    text-decoration: none;
}

span.footnote a:active, span.footnoteref a:active {
    text-decoration: underline;
}

#footnotes {
    padding-top: 0.75em;
    padding-bottom: 0.75em;
    margin-bottom: 0.625em;
}

#footnotes hr {
    width: 20%;
    min-width: 6.25em;
    margin: -.25em 0 .75em 0;
    border-width: 1px 0 0 0;
}

#footnotes .footnote {
    padding: 0 0.375em;
    line-height: 1.3;
    font-size: 0.875em;
    margin-left: 1.2em;
    text-indent: -1.2em;
    margin-bottom: .2em;
}

#footnotes .footnote a:first-of-type {
    font-weight: bold;
    text-decoration: none;
}

#footnotes .footnote:last-of-type {
    margin-bottom: 0;
}

#content #footnotes {
    margin-top: -0.625em;
    margin-bottom: 0;
    padding: 0.75em 0;
}

.gist .file-data > table {
    border: 0;
    background: #fff;
    width: 100%;
    margin-bottom: 0;
}

.gist .file-data > table td.line-data {
    width: 99%;
}

div.unbreakable {
    page-break-inside: avoid;
}

.big {
    font-size: x-large;
}

.small {
    font-size: smaller;
}

.underline {
    text-decoration: underline;
}

.overline {
    text-decoration: overline;
}

.line-through {
    text-decoration: line-through;
}

.aqua {
    color: #00bfbf;
}

.aqua-background {
    background-color: #00fafa;
}

.black {
    color: black;
}

.black-background {
    background-color: black;
}

.blue {
    color: #0000bf;
}

.blue-background {
    background-color: #0000fa;
}

.fuchsia {
    color: #bf00bf;
}

.fuchsia-background {
    background-color: #fa00fa;
}

.gray {
    color: #606060;
}

.gray-background {
    background-color: #7d7d7d;
}

.green {
    color: #006000;
}

.green-background {
    background-color: #007d00;
}

.lime {
    color: #00bf00;
}

.lime-background {
    background-color: #00fa00;
}

.maroon {
    color: #600000;
}

.maroon-background {
    background-color: #7d0000;
}

.navy {
    color: #000060;
}

.navy-background {
    background-color: #00007d;
}

.olive {
    color: #606000;
}

.olive-background {
    background-color: #7d7d00;
}

.purple {
    color: #600060;
}

.purple-background {
    background-color: #7d007d;
}

.red {
    color: #bf0000;
}

.red-background {
    background-color: #fa0000;
}

.silver {
    color: #909090;
}

.silver-background {
    background-color: #bcbcbc;
}

.teal {
    color: #006060;
}

.teal-background {
    background-color: #007d7d;
}

.white {
    color: #bfbfbf;
}

.white-background {
    background-color: #fafafa;
}

.yellow {
    color: #bfbf00;
}

.yellow-background {
    background-color: #fafa00;
}

span.icon > .fa {
    cursor: default;
}

.admonitionblock td.icon [class^="fa icon-"] {
    font-size: 2.5em;
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
    cursor: default;
}

.admonitionblock td.icon .icon-note:before {
    content: "\f05a";
    color: #19407c;
}

.admonitionblock td.icon .icon-tip:before {
    content: "\f0eb";
    text-shadow: 1px 1px 2px rgba(155, 155, 0, 0.8);
    color: #111;
}

.admonitionblock td.icon .icon-warning:before {
    content: "\f071";
    color: #bf6900;
}

.admonitionblock td.icon .icon-caution:before {
    content: "\f06d";
    color: #bf3400;
}

.admonitionblock td.icon .icon-important:before {
    content: "\f06a";
    color: #bf0000;
}

.conum[data-value] {
    display: inline-block;
    color: #fff !important;
    background-color: rgba(0, 0, 0, 0.8);
    -webkit-border-radius: 100px;
    border-radius: 100px;
    text-align: center;
    font-size: 0.75em;
    width: 1.67em;
    height: 1.67em;
    line-height: 1.67em;
    font-family: "Open Sans", "DejaVu Sans", sans-serif;
    font-style: normal;
    font-weight: bold;
}

.conum[data-value] * {
    color: #fff !important;
}

.conum[data-value] + b {
    display: none;
}

.conum[data-value]:after {
    content: attr(data-value);
}

pre .conum[data-value] {
    position: relative;
    top: -0.125em;
}

b.conum * {
    color: inherit !important;
}

.conum:not([data-value]):empty {
    display: none;
}

h1, h2 {
    letter-spacing: -0.01em;
}

dt, th.tableblock, td.content {
    text-rendering: optimizeLegibility;
}

p, td.content {
    letter-spacing: -0.01em;
}

p strong, td.content strong {
    letter-spacing: -0.005em;
}

p, blockquote, dt, td.content {
    font-size: 1.0625rem;
}

p {
    margin-bottom: 1.25rem;
}

.sidebarblock p, .sidebarblock dt, .sidebarblock td.content, p.tableblock {
    font-size: 1em;
}

.exampleblock > .content {
    background-color: #fffef7;
    border-color: #e0e0dc;
    -webkit-box-shadow: 0 1px 4px #e0e0dc;
    box-shadow: 0 1px 4px #e0e0dc;
}

.print-only {
    display: none !important;
}

@media print {
    @page {
        margin: 1.25cm 0.75cm;
    }

    * {
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        text-shadow: none !important;
    }

    a {
        color: inherit !important;
        text-decoration: underline !important;
    }

    a.bare, a[href^="#"], a[href^="mailto:"] {
        text-decoration: none !important;
    }

    a[href^="http:"]:not(.bare):after, a[href^="https:"]:not(.bare):after, a[href^="mailto:"]:not(.bare):after {
        content: "(" attr(href) ")";
        display: inline-block;
        font-size: 0.875em;
        padding-left: 0.25em;
    }

    abbr[title]:after {
        content: " (" attr(title) ")";
    }

    pre, blockquote, tr, img {
        page-break-inside: avoid;
    }

    thead {
        display: table-header-group;
    }

    img {
        max-width: 100% !important;
    }

    p, blockquote, dt, td.content {
        font-size: 1em;
        orphans: 3;
        widows: 3;
    }

    h2, h3, #toctitle, .sidebarblock > .content > .title, #toctitle, .sidebarblock > .content > .title {
        page-break-after: avoid;
    }

    #toc, .sidebarblock, .exampleblock > .content {
        background: none !important;
    }

    #toc {
        border-bottom: 1px solid #ddddd8 !important;
        padding-bottom: 0 !important;
    }

    .sect1 {
        padding-bottom: 0 !important;
    }

    .sect1 + .sect1 {
        border: 0 !important;
    }

    #header > h1:first-child {
        margin-top: 1.25rem;
    }

    body.book #header {
        text-align: center;
    }

    body.book #header > h1:first-child {
        border: 0 !important;
        margin: 2.5em 0 1em 0;
    }

    body.book #header .details {
        border: 0 !important;
        display: block;
        padding: 0 !important;
    }

    body.book #header .details span:first-child {
        margin-left: 0 !important;
    }

    body.book #header .details br {
        display: block;
    }

    body.book #header .details br + span:before {
        content: none !important;
    }

    body.book #toc {
        border: 0 !important;
        text-align: left !important;
        padding: 0 !important;
        margin: 0 !important;
    }

    body.book #toc, body.book #preamble, body.book h1.sect0, body.book .sect1 > h2 {
        page-break-before: always;
    }

    .listingblock code[data-lang]:before {
        display: block;
    }

    #footer {
        background: none !important;
        padding: 0 0.9375em;
    }

    #footer-text {
        color: rgba(0, 0, 0, 0.6) !important;
        font-size: 0.9em;
    }

    .hide-on-print {
        display: none !important;
    }

    .print-only {
        display: block !important;
    }

    .hide-for-print {
        display: none !important;
    }

    .show-for-print {
        display: inherit !important;
    }
}

</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
/* Stylesheet for CodeRay to match GitHub theme | MIT License | http://foundation.zurb.com */
/*pre.CodeRay {background-color:#f7f7f8;}*/
.CodeRay .line-numbers{border-right:1px solid #d8d8d8;padding:0 0.5em 0 .25em}
.CodeRay span.line-numbers{display:inline-block;margin-right:.5em;color:rgba(0,0,0,.3)}
.CodeRay .line-numbers strong{color:rgba(0,0,0,.4)}
table.CodeRay{border-collapse:separate;border-spacing:0;margin-bottom:0;border:0;background:none}
table.CodeRay td{vertical-align: top;line-height:1.45}
table.CodeRay td.line-numbers{text-align:right}
table.CodeRay td.line-numbers>pre{padding:0;color:rgba(0,0,0,.3)}
table.CodeRay td.code{padding:0 0 0 .5em}
table.CodeRay td.code>pre{padding:0}
.CodeRay .debug{color:#fff !important;background:#000080 !important}
.CodeRay .annotation{color:#007}
.CodeRay .attribute-name{color:#000080}
.CodeRay .attribute-value{color:#700}
.CodeRay .binary{color:#509}
.CodeRay .comment{color:#998;font-style:italic}
.CodeRay .char{color:#04d}
.CodeRay .char .content{color:#04d}
.CodeRay .char .delimiter{color:#039}
.CodeRay .class{color:#458;font-weight:bold}
.CodeRay .complex{color:#a08}
.CodeRay .constant,.CodeRay .predefined-constant{color:#008080}
.CodeRay .color{color:#099}
.CodeRay .class-variable{color:#369}
.CodeRay .decorator{color:#b0b}
.CodeRay .definition{color:#099}
.CodeRay .delimiter{color:#000}
.CodeRay .doc{color:#970}
.CodeRay .doctype{color:#34b}
.CodeRay .doc-string{color:#d42}
.CodeRay .escape{color:#666}
.CodeRay .entity{color:#800}
.CodeRay .error{color:#808}
.CodeRay .exception{color:inherit}
.CodeRay .filename{color:#099}
.CodeRay .function{color:#900;font-weight:bold}
.CodeRay .global-variable{color:#008080}
.CodeRay .hex{color:#058}
.CodeRay .integer,.CodeRay .float{color:#099}
.CodeRay .include{color:#555}
.CodeRay .inline{color:#000}
.CodeRay .inline .inline{background:#ccc}
.CodeRay .inline .inline .inline{background:#bbb}
.CodeRay .inline .inline-delimiter{color:#d14}
.CodeRay .inline-delimiter{color:#d14}
.CodeRay .important{color:#555;font-weight:bold}
.CodeRay .interpreted{color:#b2b}
.CodeRay .instance-variable{color:#008080}
.CodeRay .label{color:#970}
.CodeRay .local-variable{color:#963}
.CodeRay .octal{color:#40e}
.CodeRay .predefined{color:#369}
.CodeRay .preprocessor{color:#579}
.CodeRay .pseudo-class{color:#555}
.CodeRay .directive{font-weight:bold}
.CodeRay .type{font-weight:bold}
.CodeRay .predefined-type{color:inherit}
.CodeRay .reserved,.CodeRay .keyword {color:#000;font-weight:bold}
.CodeRay .key{color:#808}
.CodeRay .key .delimiter{color:#606}
.CodeRay .key .char{color:#80f}
.CodeRay .value{color:#088}
.CodeRay .regexp .delimiter{color:#808}
.CodeRay .regexp .content{color:#808}
.CodeRay .regexp .modifier{color:#808}
.CodeRay .regexp .char{color:#d14}
.CodeRay .regexp .function{color:#404;font-weight:bold}
.CodeRay .string{color:#d20}
.CodeRay .string .string .string{background:#ffd0d0}
.CodeRay .string .content{color:#d14}
.CodeRay .string .char{color:#d14}
.CodeRay .string .delimiter{color:#d14}
.CodeRay .shell{color:#d14}
.CodeRay .shell .delimiter{color:#d14}
.CodeRay .symbol{color:#990073}
.CodeRay .symbol .content{color:#a60}
.CodeRay .symbol .delimiter{color:#630}
.CodeRay .tag{color:#008080}
.CodeRay .tag-special{color:#d70}
.CodeRay .variable{color:#036}
.CodeRay .insert{background:#afa}
.CodeRay .delete{background:#faa}
.CodeRay .change{color:#aaf;background:#007}
.CodeRay .head{color:#f8f;background:#505}
.CodeRay .insert .insert{color:#080}
.CodeRay .delete .delete{color:#800}
.CodeRay .change .change{color:#66f}
.CodeRay .head .head{color:#f4f}
</style>
<link rel="stylesheet" href="geoapi.css">
</head>
<body class="book toc2 toc-left">
<div id="header">
<h1>OGC GeoAPI 3.1/4.0</h1>
<div id="toc" class="toc2">
<div id="toctitle">Table of Contents</div>
<ul class="sectlevel1">
<li><a href="#scope">1. Scope</a></li>
<li><a href="#conformance">2. Conformance</a></li>
<li><a href="#references">3. References</a></li>
<li><a href="#terms-and-definitions">4. Terms and Definitions</a>
<ul class="sectlevel2">
<li><a href="#term-API">4.1. Application Programming Interface (API)</a></li>
<li><a href="#term-conceptual-model">4.2. conceptual model</a></li>
<li><a href="#term-constraint">4.3. constraint</a></li>
<li><a href="#term-coordinate">4.4. coordinate</a></li>
<li><a href="#term-coordinate-operation">4.5. coordinate operation</a></li>
<li><a href="#term-crs">4.6. coordinate reference system</a></li>
<li><a href="#term-coverage">4.7. coverage</a></li>
<li><a href="#term-dataset">4.8. dataset</a></li>
<li><a href="#term-datatype">4.9. datatype</a></li>
<li><a href="#term-dynamic-attribute">4.10. dynamic attribute</a></li>
<li><a href="#term-feature">4.11. feature</a></li>
<li><a href="#term-feature-attribute">4.12. feature attribute</a></li>
<li><a href="#term-feature-operation">4.13. feature operation</a></li>
<li><a href="#term-geographic-feature">4.14. geographic feature</a></li>
<li><a href="#term-geometric-object">4.15. geometric object</a></li>
<li><a href="#term-interface">4.16. interface</a></li>
<li><a href="#term-java">4.17. Java</a></li>
<li><a href="#term-metadata">4.18. metadata</a></li>
<li><a href="#term-moving-feature">4.19. moving feature</a></li>
<li><a href="#term-multiplicity">4.20. multiplicity</a></li>
<li><a href="#term-package">4.21. package</a></li>
<li><a href="#term-property">4.22. property</a></li>
<li><a href="#term-python">4.23. Python</a></li>
<li><a href="#term-trajectory">4.24. trajectory</a></li>
</ul>
</li>
<li><a href="#conventions">5. Conventions</a>
<ul class="sectlevel2">
<li><a href="#UML-notation">5.1. UML notation</a></li>
<li><a href="#type-terminology">5.2. Type terminology</a></li>
<li><a href="#abbreviations">5.3. Abbreviated terms</a></li>
<li><a href="#identifiers">5.4. Identifiers</a>
<ul class="sectlevel3">
<li><a href="#package-namespaces">5.4.1. Package namespaces</a></li>
</ul>
</li>
</ul>
</li>
<li><a href="#overview">6. Geospatial API overview</a>
<ul class="sectlevel2">
<li><a href="#general-mapping">6.1. General mapping rules</a>
<ul class="sectlevel3">
<li><a href="#naming">6.1.1. Naming conventions</a></li>
<li><a href="#multiplicity">6.1.2. Multiplicity conventions</a></li>
<li><a href="#annotations">6.1.3. Annotated API</a></li>
<li><a href="#derived-properties">6.1.4. Derived methods</a></li>
</ul>
</li>
<li><a href="#core-types">6.2. Core data types mapping</a>
<ul class="sectlevel3">
<li><a href="#primitives">6.2.1. Primitive types</a></li>
<li><a href="#datetime">6.2.2. Date and time</a></li>
<li><a href="#collections">6.2.3. Collections</a></li>
<li><a href="#controlled-vocabulary">6.2.4. Controlled vocabulary</a></li>
<li><a href="#generic-name">6.2.5. Name types</a></li>
<li><a href="#records">6.2.6. Record types</a></li>
</ul>
</li>
<li><a href="#internationalization">6.3. Internationalization</a></li>
<li><a href="#uom">6.4. Units of measurement</a></li>
<li><a href="#util-departures">6.5. Departure from ISO 19103</a></li>
<li><a href="#metadata">6.6. Metadata packages</a>
<ul class="sectlevel3">
<li><a href="#metadata-mapping">6.6.1. Package mapping</a></li>
<li><a href="#metadata-reference-system">6.6.2. Reference systems</a></li>
<li><a href="#metadata-usage">6.6.3. How to use</a></li>
<li><a href="#metadata-departures">6.6.4. Departures from ISO 19115</a></li>
</ul>
</li>
</ul>
</li>
<li><a href="#requirements">7. Requirements</a>
<ul class="sectlevel2">
<li><a href="#requirements-A">7.1. Requirement class A</a>
<ul class="sectlevel3">
<li><a href="#requirement-01">7.1.1. Requirement 1</a></li>
</ul>
</li>
</ul>
</li>
<li><a href="#conformance-tests">Annex A: Conformance Class Test Suite (Normative)</a>
<ul class="sectlevel2">
<li><a href="#tests-A">A.1. Conformance Class A</a>
<ul class="sectlevel3">
<li><a href="#test-01">A.1.1. Requirement 1</a></li>
</ul>
</li>
</ul>
</li>
<li><a href="#conformance-levels">Annex B: Conformance Level (Normative)</a></li>
<li><a href="#java">Annex C: Java Profile (Normative)</a></li>
<li><a href="#python">Annex D: Python Profile (Normative)</a></li>
<li><a href="#examples">Annex E: Examples</a>
<ul class="sectlevel2">
<li><a href="#UML-introspection">E.1. UML at runtime</a>
<ul class="sectlevel3">
<li><a href="#UML-java">E.1.1. Java example</a></li>
</ul>
</li>
</ul>
</li>
<li><a href="#">Annex F: Revision History</a></li>
<li><a href="#bibliography">Annex G: Bibliography</a></li>
</ul>
</div>
</div>
<div id="content">
<div id="preamble">
<div class="sectionbody">
<div style="page-break-after: always;"></div>
<table class="tableblock frame-none grid-none stretch">
<colgroup>
<col style="width: 100%;">
</colgroup>
<tbody>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><strong class="big">Open Geospatial Consortium</strong></p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Submission Date: &lt;yyyy-mm-dd&gt;</p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Approval Date:   &lt;yyyy-mm-dd&gt;</p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Publication Date:   &lt;yyyy-mm-dd&gt;</p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">External identifier of this OGC<sup>®</sup> document: <a href="http://www.opengis.net/doc/is/geoapi/{m.n}" class="bare">http://www.opengis.net/doc/is/geoapi/{m.n}</a> (TODO)</p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Internal reference number of this OGC<sup>®</sup> document:    YY-nnnrx</p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Version: n.n</p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Category: OGC<sup>®</sup> Implementation Specification</p></td>
</tr>
<tr>
<td class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Editor:   Martin Desruisseaux</p></td>
</tr>
</tbody>
</table>
<table class="tableblock frame-none grid-all stretch">
<colgroup>
<col style="width: 100%;">
</colgroup>
<tbody>
<tr>
<td class="tableblock halign-center valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><strong class="big">OGC GeoAPI 3.1/4.0</strong></p></td>
</tr>
</tbody>
</table>
<table class="tableblock frame-none grid-none stretch">
<colgroup>
<col style="width: 100%;">
</colgroup>
<tbody>
<tr>
<td class="tableblock halign-center valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><strong>Copyright notice</strong></p></td>
</tr>
<tr>
<td class="tableblock halign-center valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Copyright © 2009-2018 Open Geospatial Consortium</p></td>
</tr>
<tr>
<td class="tableblock halign-center valign-top" style="background-color: #FFFFFF;"><p class="tableblock">To obtain additional rights of use, visit <a href="http://www.opengeospatial.org/legal/" class="bare">http://www.opengeospatial.org/legal/</a></p></td>
</tr>
</tbody>
</table>
<table class="tableblock frame-none grid-all stretch">
<colgroup>
<col style="width: 100%;">
</colgroup>
<tbody>
<tr>
<td class="tableblock halign-center valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><strong>Warning</strong></p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>This document is not an OGC Standard. This document is distributed for review and comment.
This document is subject to change without notice and may not be referred to as an OGC Standard.</p>
</div>
<div class="paragraph">
<p>Recipients of this document are invited to submit, with their comments, notification of any relevant patent rights
of which they are aware and to provide supporting documentation.</p>
</div>
<table class="tableblock frame-all grid-none" style="width: 50%;">
<colgroup>
<col style="width: 100%;">
</colgroup>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Document type:   	OGC® Standard</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Document stage:   	Draft</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Document language: 	English</p></td>
</tr>
</tbody>
</table>
<div style="page-break-after: always;"></div>
<div class="paragraph">
<p>License Agreement</p>
</div>
<div class="paragraph">
<p><span class="small">Permission is hereby granted by the Open Geospatial Consortium, ("Licensor"), free of charge and subject to the
terms set forth below, to any person obtaining a copy of this Intellectual Property and any associated documentation,
to deal in the Intellectual Property without restriction (except as set forth below), including without limitation the
rights to implement, use, copy, modify, merge, publish, distribute, and/or sublicense copies of the Intellectual Property,
and to permit persons to whom the Intellectual Property is furnished to do so, provided that all copyright notices on the
intellectual property are retained intact and that each person to whom the Intellectual Property is furnished agrees
to the terms of this Agreement.</span></p>
</div>
<div class="paragraph">
<p><span class="small">If you modify the Intellectual Property, all copies of the modified Intellectual Property must include,
in addition to the above copyright notice, a notice that the Intellectual Property includes modifications that
have not been approved or adopted by LICENSOR.</span></p>
</div>
<div class="paragraph">
<p><span class="small">THIS LICENSE IS A COPYRIGHT LICENSE ONLY, AND DOES NOT CONVEY ANY RIGHTS UNDER ANY PATENTS
THAT MAY BE IN FORCE ANYWHERE IN THE WORLD.</span></p>
</div>
<div class="paragraph">
<p><span class="small">THE INTELLECTUAL PROPERTY IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND NONINFRINGEMENT OF THIRD PARTY RIGHTS.
THE COPYRIGHT HOLDER OR HOLDERS INCLUDED IN THIS NOTICE DO NOT WARRANT THAT THE FUNCTIONS CONTAINED IN THE INTELLECTUAL
PROPERTY WILL MEET YOUR REQUIREMENTS OR THAT THE OPERATION OF THE INTELLECTUAL PROPERTY WILL BE UNINTERRUPTED OR ERROR FREE.
ANY USE OF THE INTELLECTUAL PROPERTY SHALL BE MADE ENTIRELY AT THE USER’S OWN RISK. IN NO EVENT SHALL THE COPYRIGHT HOLDER
OR ANY CONTRIBUTOR OF INTELLECTUAL PROPERTY RIGHTS TO THE INTELLECTUAL PROPERTY BE LIABLE FOR ANY CLAIM, OR ANY DIRECT, SPECIAL,
INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING FROM ANY ALLEGED INFRINGEMENT OR ANY LOSS OF USE, DATA
OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR UNDER ANY OTHER LEGAL THEORY, ARISING OUT OF OR IN CONNECTION WITH
THE IMPLEMENTATION, USE, COMMERCIALIZATION OR PERFORMANCE OF THIS INTELLECTUAL PROPERTY.</span></p>
</div>
<div class="paragraph">
<p><span class="small">This license is effective until terminated. You may terminate it at any time by destroying the Intellectual Property
together with all copies in any form. The license will also terminate if you fail to comply with any term or condition of this
Agreement. Except as provided in the following sentence, no such termination of this license shall require the termination of
any third party end-user sublicense to the Intellectual Property which is in force as of the date of notice of such termination.
In addition, should the Intellectual Property, or the operation of the Intellectual Property, infringe, or in LICENSOR&#8217;s sole
opinion be likely to infringe, any patent, copyright, trademark or other right of a third party, you agree that LICENSOR, in
its sole discretion, may terminate this license without any compensation or liability to you, your licensees or any other party.
You agree upon termination of any kind to destroy or cause to be destroyed the Intellectual Property together with all copies
in any form, whether held by you or by any third party.</span></p>
</div>
<div class="paragraph">
<p><span class="small">Except as contained in this notice, the name of LICENSOR or of any other holder of a copyright in all or part of
the Intellectual Property shall not be used in advertising or otherwise to promote the sale, use or other dealings in this
Intellectual Property without prior written authorization of LICENSOR or such copyright holder. LICENSOR is and shall at all
times be the sole entity that may authorize you or any third party to use certification marks, trademarks or other special
designations to indicate compliance with any LICENSOR standards or specifications.
This Agreement is governed by the laws of the Commonwealth of Massachusetts. The application to this Agreement
of the United Nations Convention on Contracts for the International Sale of Goods is hereby expressly excluded.
In the event any provision of this Agreement shall be deemed unenforceable, void or invalid, such provision shall be modified
so as to make it valid and enforceable, and as so modified the entire Agreement shall remain in full force and effect.
No decision, action or inaction by LICENSOR shall be construed to be a waiver of any rights or remedies available to it.</span></p>
</div>
<div style="page-break-after: always;"></div>
<!-- toc disabled -->
<div style="page-break-after: always;"></div>
<div class="paragraph">
<p><strong class="big">i.     Abstract</strong></p>
</div>
<div class="paragraph">
<p>The GeoAPI Implementation Standard defines application programming interfaces (API) in some programming languages
(currently Java and Python) for geospatial applications.
The API includes a set of types and methods which can be used for the manipulation of geographic information structured
following the specifications adopted by the Technical Committee 211 of the International Organization for Standardization (ISO)
and by the Open Geospatial Consortium (OGC).
Those interfaces standardize the informatics contract between the client code,
which manipulates normalized data structures of geographic information based on the published API,
and the library code able both to instantiate and operate on these data structures
according to the rules required by the published API and by the ISO and OGC standards.</p>
</div>
<div class="paragraph">
<p>This standard complements existing OGC standards by defining language specific layers of normalization.
This standard does not replace the core standards developing the OGC/ISO abstract model but complements those documents
for developers by documenting the mapping of types and methods from the abstract model into some programming languages,
providing standard interfaces in the <code>org.opengis</code> or <code>opengis</code> namespaces and explaining the use of GeoAPI interfaces.</p>
</div>
<div class="paragraph">
<p><strong class="big">ii.    Keywords</strong></p>
</div>
<div class="paragraph">
<p>The following are keywords to be used by search engines and document catalogues.</p>
</div>
<div class="paragraph">
<p>ogcdoc, OGC document, GeoAPI, programming, Java, Python, interface, geospatial, metadata, referencing, feature</p>
</div>
<div class="paragraph">
<p><strong class="big">iii.   Preface</strong></p>
</div>
<div id="preface" class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph">
<p>There is various libraries for helping developers to process geospatial data from programming languages like Java and Python.
But the proliferation of API variations degrade interoperability.
Since each library defines its own Application Programming Interface (API),
the choice of a particular library result in a vendor lock-in situation even with open-source softwares.
For example it is difficult for a Web Map Service (WMS) implementation to replace its map projection engine
if all available engines use incompatible APIs.
Standard API in programmatic language can reduce such vendor lock-in by providing a layer which separates client code,
which call the API, from library code, which implements the API.
This follows a similar pattern to the well known JDBC (in Java language) or ODBC (in C/C++ language) API
which provides standardized interfaces to databases.
Clients can use those APIs without concern for the particular implementation which they will use.</p>
</div>
<div class="paragraph">
<p>This GeoAPI standard evolved from an effort at the Open Geospatial Consortium (OGC) and in the free software community
focused on developing a library of interfaces defining a coherent data model for the manipulation of geospatial data
based on the data model defined in the OGC Abstract Specifications.
GeoAPI interfaces originates with the publication in January 2001 of the implementation specification
OGC 01-009 <em>Coordinate Transformation Services</em> Revision 1.00 (Martin Daly, ed.)
which included a set of interfaces written in different programming languages and in the <code>org.opengis</code> namespace.
The GeoAPI project started in 2003 as an effort from several contributors to develop a set of Java language interfaces
which could be shared between several projects.
The GeoAPI project subsequently considered the interfaces of OGC 01-009 as version 0.1 of GeoAPI
and started working on GeoAPI 1.0 in collaboration with developers writing the OGC specification <em>Geographic Objects</em>.
Subsequently, the Open Geospatial Consortium jettisoned its own Abstract Specifications and adopted,
as the basis for further work, the standards developed by the Technical Committee 211 of the
International Organization for Standardization (ISO) in its ISO 19100 series.
The GeoAPI project therefore realigned its interfaces with those standards.
In 2003, version 1.0 of GeoAPI interfaces was released to match the release of the first public draft
of the implementation specification OGC 03-064 <em>GO-1 Application Objects</em> Version 1.0 (Greg Reynolds, ed.).
The standardization effort of GO-1 took a couple of years during which extensive work was made on GeoAPI interfaces.
Release 2.0 of GeoAPI was made at the time of the final publication of the GO-1 specification in 2005.
GO-1 has been retired later, but a new working group has been formed in 2009 for continuing GeoAPI development
with a more restricted scope: to provide interfaces for existing OGC standards only, without defining new conceptual models.
GeoAPI 3.0.0 has been released in 2011 and GeoAPI 3.0.1 in 2017.</p>
</div>
<div class="paragraph">
<p>GeoAPI interfaces are derived from OGC/ISO conceptual models described by Unified Modeling Language (UML) diagrams.
The XML schemas are generally not used (except when there is no UML diagrams describing the model)
because they carry XML-specific constraints that do not apply to programming languages.
For example querying the coordinate system associated to a Coordinate Reference System (CRS) is a single method call in GeoAPI.
But this single operation would have required more than 50 lines of code if the API was generated
from Geographic Markup Language (GML) schema instead than from the UML diagrams of abstract models.</p>
</div>
<div class="paragraph">
<p>The interfaces described in this standard follow closely, without introducing new concepts,
from the previously published standards of the Open Geospatial Consortium and the International Organization for Standardization.
Nonetheless, attention is drawn to the possibility that some of the elements of this document may be the subject of patent rights.
The Open Geospatial Consortium shall not be held responsible for identifying any or all such patent rights.</p>
</div>
<div class="paragraph">
<p>Recipients of this document are requested to submit, with their comments, notification of any relevant patent claims
or other intellectual property rights of which they may be aware that might be infringed by any implementation of the
standard set forth in this document, and to provide supporting documentation.</p>
</div>
</td>
</tr>
</table>
</div>
<div class="paragraph">
<p><strong class="big">iv.    Submitting organizations</strong></p>
</div>
<div class="paragraph">
<p>The following organizations submitted this Document to the Open Geospatial Consortium (OGC):</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<colgroup>
<col style="width: 100%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Organization name(s)</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Geomatys</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">TODO</p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p><strong class="big">v.     Submitters</strong></p>
</div>
<div class="paragraph">
<p>All questions regarding this submission should be directed to the editor or the submitters:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Name</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Affiliation</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Martin Desruisseaux</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Geomatys</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect1">
<h2 id="scope"><a class="anchor" href="#scope"></a>1. Scope</h2>
<div class="sectionbody">
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph">
<p>GeoAPI contains a series of interfaces and classes in several packages which interpret into some programming languages
(currently Java and Python) the data model and UML types of the ISO and OGC standards documents.
The interfaces includes documentation which complement the injunctions of the OGC/ISO specifications by explaining
particularities of GeoAPI: interpretations made of the specifications where there was room for choice,
constraints specific to each programming language,
or standard patterns of behavior expected by the community of a programming language.
This document explains GeoAPI interfaces and defines its use by library code implementing the API and by client code calling the API.
Jointly with the interface definitions, this work aims to provide:</p>
</div>
<div class="ulist">
<ul>
<li>
<p>a carefully considered interpretation of the OGC specifications for Java and Python languages,</p>
</li>
<li>
<p>a base structure to facilitate the creation of software libraries which implement OGC standards,</p>
</li>
<li>
<p>a well defined, full documented binding reducing the programming effort of using the OGC abstract model,</p>
</li>
<li>
<p>and to facilitate the portability of application code between different implementations.</p>
</li>
</ul>
</div>
<div class="paragraph">
<p>The interfaces defined in this standard provide one way to structure the use of the Java and Python languages
to implement softwares which follow the design and intents of the OGC/ISO specifications.
The creators of the GeoAPI interfaces consider this approach as an effective compromise between the OGC specifications,
the requirements of above-cited programming languages, and the tradition of the core libraries of those languages.</p>
</div>
<div class="paragraph">
<p>This version of the standard does not propose a complete set of interfaces covering the entire set of OGC/ISO abstract standards,
but focuses on an initial group of interfaces only.
This initial group of interfaces covers enough of the abstract model to permit the description of geospatial metadata,
the definition of coordinate reference systems and to enable operations on coordinate tuples between different reference systems.
The work writing interfaces matching other OGC specifications is done in the “pending” directory of the GeoAPI project.
It is expected that these other interfaces will be proposed for standardization in subsequent revisions of this specification
but the interfaces must first have been implemented, ideally several times, and then tested extensively by use.</p>
</div>
<div class="paragraph">
<p>Version 3.1 and 4.0 of GeoAPI covers the base of the OGC/ISO Abstract Model for geographic information.
GeoAPI provides utilities, base types, metadata structures, geo-referencing and a feature model.
The geo-referencing data elements enable the creation of reference systems for spatial coordinates
and mathematical operators to convert coordinates from one coordinate reference system to another.
This version of the standard covers the specifications ISO 19103, ISO 19115, ISO 19111
(completed by some elements from the closely related OGC™ specification OGC 01-009),
ISO 19109 and four elements from ISO 19107 necessary to the implementation of ISO 19111.
Future versions of this specification are expected to expand this set of interfaces to cover more models
of the OGC Abstract Specification series, including notably geometry data structures,
with the “pending” portion of the GeoAPI project already exploring these new areas.</p>
</div>
</td>
</tr>
</table>
</div>
</div>
</div>
<div class="sect1">
<h2 id="conformance"><a class="anchor" href="#conformance"></a>2. Conformance</h2>
<div class="sectionbody">
<div class="paragraph">
<p>This standard defines interfaces in Java and Python programming languages.
The normative publication of the interfaces occurs in the ASCII or binary format specific to each target language.
The interfaces are distributed in ZIP bundles along with the API documentation.
An online version of the API documentation, which may contain fixes for errata discovered after publication of this specification,
is available at the URLs listed below:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 1. Distribution formats</caption>
<colgroup>
<col style="width: 14.2857%;">
<col style="width: 42.8571%;">
<col style="width: 42.8572%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Interfaces:</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Java Archive (JAR) binary</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Python source files (.py)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Documentation:</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Javadoc as HTML files</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Sphinx generated pages as HTML files</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Online version:</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><a href="http://www.geoapi.org/snapshot/javadoc/" class="bare">http://www.geoapi.org/snapshot/javadoc/</a></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><a href="http://www.geoapi.org/snapshot/python/" class="bare">http://www.geoapi.org/snapshot/python/</a></p></td>
</tr>
</tbody>
</table>
<div class="admonitionblock warning">
<table>
<tr>
<td class="icon">
<i class="fa icon-warning" title="Warning"></i>
</td>
<td class="content">
<div class="paragraph">
<p>The <code>snapshot</code> elements in above URLs will be replaced by <code>3.1</code> and <code>4.0</code> after release (TODO).</p>
</div>
</td>
</tr>
</table>
</div>
<div class="paragraph">
<p>This specification makes certain requirements of libraries implementing this API and defines several conformance classes
for implementations covering different packages of the API or providing different levels of complexity in their implementations.
GeoAPI provides a test suite through which to establish conformance of GeoAPI implementations.
Requirements for 2 standardization target types are considered:</p>
</div>
<div class="ulist">
<ul>
<li>
<p><strong>Libraries</strong> which provide software components for building geospatial applications.</p>
</li>
<li>
<p><strong>Applications</strong> which use the above-cited software components.</p>
</li>
</ul>
</div>
<div class="paragraph">
<p>The second target type (applications) leaves more freedom than the first target type (libraries).
In particular, applications are free to delete any types, methods or functionalities that they do not use.</p>
</div>
<div class="paragraph">
<p>Conformance with this standard shall be checked using all the relevant tests specified in <a href="#conformance-tests">Annex A (normative)</a> of this document.
The framework, concepts, and methodology for testing, and the criteria to be achieved to claim conformance are specified in the
OGC Compliance Testing Policies and Procedures and the OGC Compliance Testing web site.
In order to conform to this OGC® interface standard, a software implementation shall choose to implement:</p>
</div>
<div class="ulist">
<ul>
<li>
<p>Any one of the conformance levels specified in <a href="#conformance-levels">Annex B (normative)</a>.</p>
</li>
<li>
<p>Any one of the Distributed Computing Platform profiles specified in <a href="#java">Annexes C</a> through <a href="#python">D</a> (normative).</p>
</li>
</ul>
</div>
<div class="paragraph">
<p>All requirements-classes and conformance-classes described in this document are owned by the standard(s) identified.</p>
</div>
</div>
</div>
<div class="sect1">
<h2 id="references"><a class="anchor" href="#references"></a>3. References</h2>
<div class="sectionbody">
<div class="paragraph">
<p>The following normative documents contain provisions that, through reference in this text, constitute provisions of this document,
except for any departures from the listed specifications which are explicitly mentioned in this text.
For dated references, subsequent amendments to, or revisions of, any of these publications do not apply.
However, parties to agreements based on this specification are encouraged to investigate the possibility
of applying the most recent editions of the normative documents indicated below.
For undated references, the latest edition of the normative document referred to applies.</p>
</div>
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph bibliography">
<p>ISO / TC 211: ISO 19103:2015,    Geographic information — Conceptual schema language (2015)</p>
</div>
<div class="paragraph bibliography">
<p>ISO / TC 211: ISO 19107:2003,    Geographic information — Spatial schema (2003)</p>
</div>
<div class="paragraph bibliography">
<p>ISO / TC 211: ISO 19109:2015,    Geographic information — Rules for application schema (2015)</p>
</div>
<div class="paragraph bibliography">
<p>ISO / TC 211: ISO 19111:2007,    Geographic information — Spatial referencing by coordinates (2007)</p>
</div>
<div class="paragraph bibliography">
<p>ISO / TC 211: ISO 19115-1:2014,  Geographic information — Metadata — Fundamentals (2014)</p>
</div>
<div class="paragraph bibliography">
<p>ISO / TC 211: ISO 19115-2:2009,  Geographic information — Metadata — Extensions for imagery and gridded data (2009)</p>
</div>
<div class="paragraph bibliography">
<p>ISO / TC 211: ISO 19141:2008,    Geographic information — Schema for moving features (2008)</p>
</div>
<div class="paragraph bibliography">
<p>OGC: OGC 01‑009, OpenGIS® Implementation Specification: Coordinate Transformation Services, revision 1.00 (2001)</p>
</div>
<div class="paragraph bibliography">
<p>OGC: OGC 14-083, OGC® Moving Features Encoding Part I: XML Core (2015)</p>
</div>
<div class="paragraph bibliography">
<p>James Gosling, Bill Joy, Guy Steele, Gilad Bracha, Oracle: The Java Language Specification</p>
</div>
</td>
</tr>
</table>
</div>
</div>
</div>
<div class="sect1">
<h2 id="terms-and-definitions"><a class="anchor" href="#terms-and-definitions"></a>4. Terms and Definitions</h2>
<div class="sectionbody">
<div class="paragraph">
<p>This document uses the terms defined in Sub-clause 5.3 of [OGC 06-121r8],
which is based on the ISO/IEC Directives, Part 2, Rules for the structure and drafting of International Standards.
In particular:</p>
</div>
<div class="ulist">
<ul>
<li>
<p>SHALL (not “must”) is the verb form used to indicate a requirement to be strictly followed to conform to this standard.</p>
</li>
<li>
<p>SHOULD is the verb form used to indicate desirable ability or use, without mentioning or excluding other possibilities.</p>
</li>
<li>
<p>MAY is the verb form used to indicate an action permissible within the limits of this specification.</p>
</li>
<li>
<p>CAN is the verb form used for statements of possibility.</p>
</li>
</ul>
</div>
<div class="paragraph">
<p>For the purposes of this document, the following additional terms and definitions apply.
Further discussion about <em>type</em>, <em>class</em>, <em>interface</em>, <em>property</em>, <em>attribute</em> and <em>implementation</em> terms
can be found in the <a href="#type-terminology">conventions</a> section.</p>
</div>
<div class="sect2 term">
<h3 id="term-API"><a class="anchor" href="#term-API"></a>4.1. Application Programming Interface (API)</h3>
<div class="paragraph">
<p>a formally defined set of types and methods which establish a contract between client code which uses the API
and implementation code which provides the API</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-conceptual-model"><a class="anchor" href="#term-conceptual-model"></a>4.2. conceptual model</h3>
<div class="paragraph">
<p>model that defines concepts of a universe of discourse<br>
 [source: ISO 19101-1]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-constraint"><a class="anchor" href="#term-constraint"></a>4.3. constraint</h3>
<div class="paragraph">
<p>UML condition or restriction expressed in natural language text or in a machine readable language
for the purpose of declaring some of the semantics of an element<br>
 [source: ISO 19103]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-coordinate"><a class="anchor" href="#term-coordinate"></a>4.4. coordinate</h3>
<div class="paragraph">
<p>one of a sequence of numbers designating the position of a point<br>
<span class="small">Note 1 to entry: In a spatial coordinate reference system, the coordinate numbers are qualified by units.</span><br>
 [source: ISO 19111]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-coordinate-operation"><a class="anchor" href="#term-coordinate-operation"></a>4.5. coordinate operation</h3>
<div class="paragraph">
<p>process using a mathematical model, based on a one-to-one relationship, that changes coordinates in a source coordinate
reference system to coordinates in a target coordinate reference system, or that changes coordinates at a source coordinate
epoch to coordinates at a target coordinate epoch within the same coordinate reference system<br>
 [source: ISO 19111]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-crs"><a class="anchor" href="#term-crs"></a>4.6. coordinate reference system</h3>
<div class="paragraph">
<p>coordinate system that is related to an object by a datum<br>
<span class="small">Note 1 to entry: Geodetic and vertical datums are referred to as reference frames.</span><br>
<span class="small">Note 2 to entry: For geodetic and vertical reference frames, the object will be the Earth.
In planetary applications, geodetic and vertical reference frames may be applied to other celestial bodies.</span><br>
 [source: ISO 19111]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-coverage"><a class="anchor" href="#term-coverage"></a>4.7. coverage</h3>
<div class="paragraph">
<p>feature that acts as a function to return values from its range for any direct position within its spatial,
temporal, or spatiotemporal domain<br>
 [source: ISO 19123]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-dataset"><a class="anchor" href="#term-dataset"></a>4.8. dataset</h3>
<div class="paragraph">
<p>identifiable collection of data<br>
 [source: ISO 19115-1]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-datatype"><a class="anchor" href="#term-datatype"></a>4.9. datatype</h3>
<div class="paragraph">
<p>specification of a value domain with operations allowed on values in this domain<br>
<span class="small">Examples: <code>Integer</code>, <code>Real</code>, <code>Boolean</code>, <code>String</code> and <code>Date</code>.</span><br>
<span class="small">Note 1 to entry: Data types include primitive predefined types and user definable types.</span><br>
 [source: ISO 19103]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-dynamic-attribute"><a class="anchor" href="#term-dynamic-attribute"></a>4.10. dynamic attribute</h3>
<div class="paragraph">
<p>characteristic of a feature in which its value varies with time<br>
 [source: OGC 16-140]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-feature"><a class="anchor" href="#term-feature"></a>4.11. feature</h3>
<div class="paragraph">
<p>abstraction of a real world phenomena<br>
<span class="small">Note 1 to entry: A feature can occur as a type or an instance.
Feature type or feature instance should be used when only one is meant.</span><br>
 [source: ISO 19109]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-feature-attribute"><a class="anchor" href="#term-feature-attribute"></a>4.12. feature attribute</h3>
<div class="paragraph">
<p>characteristic of a feature<br>
<span class="small">Note 1 to entry: A feature attribute can occur as a type or an instance.
Feature attribute type or feature attribute instance is used when only one is meant.</span><br>
 [source: ISO 19109]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-feature-operation"><a class="anchor" href="#term-feature-operation"></a>4.13. feature operation</h3>
<div class="paragraph">
<p>operation that every instance of a feature type may perform<br>
 [source: ISO 19109]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-geographic-feature"><a class="anchor" href="#term-geographic-feature"></a>4.14. geographic feature</h3>
<div class="paragraph">
<p>representation of real world phenomenon associated with a location relative to the Earth<br>
 [source: ISO 19101-2]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-geometric-object"><a class="anchor" href="#term-geometric-object"></a>4.15. geometric object</h3>
<div class="paragraph">
<p>spatial object representing a geometric set<br>
 [source: ISO 19107:2003]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-interface"><a class="anchor" href="#term-interface"></a>4.16. interface</h3>
<div class="paragraph">
<p>UML classifier that represents a declaration of a set of coherent public UML features and obligations<br>
<span class="small">Note 1 to entry: An interface specifies a contract; any classifier that realizes the interface must fulfil that contract.
The obligations that can be associated with an interface are in the form of various kinds of constraints
(such as pre- and post-conditions) or protocol specifications,
which can impose ordering restrictions on interactions through the interface.</span><br>
 [source: UML 2]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-java"><a class="anchor" href="#term-java"></a>4.17. Java</h3>
<div class="paragraph">
<p>trademark of Oracle used to refer to an object oriented, single inheritance programming language
whose syntax derives from the C programming language and which is defined by the Java Language Specification</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-metadata"><a class="anchor" href="#term-metadata"></a>4.18. metadata</h3>
<div class="paragraph">
<p>data about data<br>
 [source: ISO 19115-1]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-moving-feature"><a class="anchor" href="#term-moving-feature"></a>4.19. moving feature</h3>
<div class="paragraph">
<p>feature whose location changes over time<br>
<span class="small">Note 1 to entry: Its base representation uses a local origin and local coordinate vectors
of a geometric object at a given reference time.</span><br>
<span class="small">Note 2 to entry: The local origin and ordinate vectors establish an engineering coordinate
reference system (ISO 19111), also called a local frame or a local Euclidean coordinate system.</span></p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-multiplicity"><a class="anchor" href="#term-multiplicity"></a>4.20. multiplicity</h3>
<div class="paragraph">
<p>UML specification of the range of allowable cardinalities that a set may assume<br>
<span class="small">Note 1 to entry: Contrast with <em>cardinality</em>, which is the number of elements in a set.</span><br>
 [source: ISO 19103]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-package"><a class="anchor" href="#term-package"></a>4.21. package</h3>
<div class="paragraph">
<p>UML general purpose mechanism for organizing elements into groups<br>
 [source: ISO 19103]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-property"><a class="anchor" href="#term-property"></a>4.22. property</h3>
<div class="paragraph">
<p>facet or attribute of an object referenced by a name<br>
 [source: ISO 19143]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-python"><a class="anchor" href="#term-python"></a>4.23. Python</h3>
<div class="paragraph">
<p>an interpreted high-level programming language for general-purpose programming<br>
 [source: Wikipedia]</p>
</div>
</div>
<div class="sect2 term">
<h3 id="term-trajectory"><a class="anchor" href="#term-trajectory"></a>4.24. trajectory</h3>
<div class="paragraph">
<p>path of a moving point described by a one parameter set of points<br>
 [source: ISO 19141]</p>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="conventions"><a class="anchor" href="#conventions"></a>5. Conventions</h2>
<div class="sectionbody">
<div class="paragraph">
<p>This section provides details for conventions used in the document.
All examples in this document illustrated by the (i) icon are informative only.</p>
</div>
<div class="sect2">
<h3 id="UML-notation"><a class="anchor" href="#UML-notation"></a>5.1. UML notation</h3>
<div class="paragraph">
<p>Unified Modeling Language (UML) static structure diagrams appearing in this document
are used as described in Subclause 5.2 of OGC Web Services Common [OGC 06-121r9].</p>
</div>
</div>
<div class="sect2">
<h3 id="type-terminology"><a class="anchor" href="#type-terminology"></a>5.2. Type terminology</h3>
<div class="paragraph">
<p>The meaning of <em>type</em>, <em>class</em>, <em>interface</em>, <em>property</em> and <em>attribute</em> can vary depending on the programming language.
This document follows the <a href="#term-interface">UML 2 definition of interface</a> as a declaration of a set of coherent public
operations, properties and obligations specifying a contract.
UML interfaces are represented in programming languages by Java interfaces and Python abstract classes.
The word <em>class</em> is generally not used in this document except in discussions specific to a programming language,
in which case the word takes the meaning defined by the target language;
in Java this is often (but not only) an implementation of an interface.</p>
</div>
<div class="paragraph">
<p>The word <em>type</em> is used as a generic term for <em>interface</em>, <em>class</em> (whatever it is in target programming languages),
<em>code list</em>, <em>enumeration</em> or the description of a <a href="#term-feature">feature</a>.
Note that code lists, enumerations and feature types are not interfaces.</p>
</div>
<div class="paragraph">
<p>The word <em>property</em> (not attribute) is used for values or associations defined by an interface.
This document reserves the word <em>attribute</em> for <a href="#term-feature-attribute">feature attributes</a> or XML attributes.</p>
</div>
<div class="paragraph">
<p>The meaning of <em>implementation</em> depends on the context.
From the perspective of OGC/ISO abstract specifications,
Java interfaces or Python abstract classes are implementations of the abstract models,
in the same way than XML Schema Definitions (XSD) are other implementations of the same abstract models.
But from the perspective of programming languages, interfaces are not implementations;
instead the word "implementation" is used for concrete classes.
In this document, the types provided by GeoAPI are said to be an implementation of abstract models,
while the concrete classes provided by vendors are said to be an implementations of GeoAPI.</p>
</div>
</div>
<div class="sect2">
<h3 id="abbreviations"><a class="anchor" href="#abbreviations"></a>5.3. Abbreviated terms</h3>
<div class="paragraph">
<p>The following symbols and abbreviated terms are used in this specification:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 2. Abbreviated terms</caption>
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">API</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Application Program Interface</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">CRS</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Coordinate Reference System</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">ISO</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">International Organization for Standardization</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">OGC</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Open Geospatial Consortium</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">UML</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Unified Modeling Language</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">URI</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Uniform Resource Identifiers</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">WKT</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Well Known Text</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">XML</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">eXtended Markup Language</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">XSD</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">XML Schema Definition</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">1D</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">One Dimensional</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">2D</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Two Dimensional</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">3D</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Three Dimensional</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">nD</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Multi-Dimensional</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">λ</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Geodetic longitude</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">φ</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Geodetic latitude</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="identifiers"><a class="anchor" href="#identifiers"></a>5.4. Identifiers</h3>
<div class="paragraph">
<p>The normative provisions in this specification are denoted by the URI:</p>
</div>
<div class="paragraph">
<p><a href="http://www.opengis.net/spec/geoapi/{m.n}" class="bare">http://www.opengis.net/spec/geoapi/{m.n}</a> (TODO)</p>
</div>
<div class="paragraph">
<p>All requirements and conformance tests that appear in this document are denoted by partial URIs which are relative to this base.</p>
</div>
<div class="sect3">
<h4 id="package-namespaces"><a class="anchor" href="#package-namespaces"></a>5.4.1. Package namespaces</h4>
<div class="paragraph">
<p>This specification uses <code>opengis</code> in the text for denoting a package or module in OGC namespace,
but the exact spelling depends on the programming language.
For example the metadata package is spelled <code>org.opengis.metadata</code> in Java
but only <code>opengis.metadata</code> (without <code>org</code> prefix) in Python.
Except in language-specific notes, this specification uses the shorter form in the text
and lets readers adapt to their programming language of interest.</p>
</div>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="overview"><a class="anchor" href="#overview"></a>6. Geospatial API overview</h2>
<div class="sectionbody">
<div class="paragraph">
<p>The GeoAPI interfaces formalizes the handling of the types defined in the specification documents
for working with geographic information adopted by the International Organization for Standardization (ISO)
and the Open Geospatial Consortium (OGC).
Whereas the specifications define types, operations and relationships using the general UML notation,
the GeoAPI types implement those standards as programming language interfaces or simple classes.
The structure of the GeoAPI library mirrors the packaging and separation of the different ISO and OGC specifications
by grouping different types and functionality in separated Java and Python language packages.</p>
</div>
<div class="imageblock">
<div class="content">
<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj4KPHN2ZyB2ZXJzaW9uPSIxLjIiIHdpZHRoPSIxNjEuMDJtbSIgaGVpZ2h0PSI3MS4wMW1tIiB2aWV3Qm94PSI5OTkgMTAwMCAxNjEwMiA3MTAxIiBwcmVzZXJ2ZUFzcGVjdFJhdGlvPSJ4TWlkWU1pZCIgZmlsbC1ydWxlPSJldmVub2RkIiBzdHJva2Utd2lkdGg9IjI4LjIyMiIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczpvb289Imh0dHA6Ly94bWwub3Blbm9mZmljZS5vcmcvc3ZnL2V4cG9ydCIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhtbG5zOnByZXNlbnRhdGlvbj0iaHR0cDovL3N1bi5jb20veG1sbnMvc3Rhcm9mZmljZS9wcmVzZW50YXRpb24iIHhtbG5zOnNtaWw9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvU01JTDIwLyIgeG1sbnM6YW5pbT0idXJuOm9hc2lzOm5hbWVzOnRjOm9wZW5kb2N1bWVudDp4bWxuczphbmltYXRpb246MS4wIiB4bWw6c3BhY2U9InByZXNlcnZlIj4KIDxkZWZzIGNsYXNzPSJDbGlwUGF0aEdyb3VwIj4KICA8Y2xpcFBhdGggaWQ9InByZXNlbnRhdGlvbl9jbGlwX3BhdGgiIGNsaXBQYXRoVW5pdHM9InVzZXJTcGFjZU9uVXNlIj4KICAgPHJlY3QgeD0iOTk5IiB5PSIxMDAwIiB3aWR0aD0iMTYxMDIiIGhlaWdodD0iNzEwMSIvPgogIDwvY2xpcFBhdGg+CiAgPGNsaXBQYXRoIGlkPSJwcmVzZW50YXRpb25fY2xpcF9wYXRoX3NocmluayIgY2xpcFBhdGhVbml0cz0idXNlclNwYWNlT25Vc2UiPgogICA8cmVjdCB4PSIxMDE1IiB5PSIxMDA3IiB3aWR0aD0iMTYwNzAiIGhlaWdodD0iNzA4NyIvPgogIDwvY2xpcFBhdGg+CiA8L2RlZnM+CiA8ZGVmcz4KICA8Zm9udCBpZD0iRW1iZWRkZWRGb250XzEiIGhvcml6LWFkdi14PSIyMDQ4Ij4KICAgPGZvbnQtZmFjZSBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8gZW1iZWRkZWQiIHVuaXRzLXBlci1lbT0iMjA0OCIgZm9udC13ZWlnaHQ9Im5vcm1hbCIgZm9udC1zdHlsZT0ibm9ybWFsIiBhc2NlbnQ9IjE4NzAiIGRlc2NlbnQ9IjQzOSIvPgogICA8bWlzc2luZy1nbHlwaCBob3Jpei1hZHYteD0iMjA0OCIgZD0iTSAwLDAgTCAyMDQ3LDAgMjA0NywyMDQ3IDAsMjA0NyAwLDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ieSIgaG9yaXotYWR2LXg9Ijk3MSIgZD0iTSAxMDY1LDEwNDIgTCA2NzAsMCBDIDYxMywtMTUzIDU1NiwtMjYwIDUwMCwtMzIxIDQ0MywtMzgyIDM3OSwtNDEyIDMwNywtNDEyIDI0MywtNDEyIDE4MCwtNDAwIDExNywtMzc3IEwgMTQzLC0yNTIgQyAxOTQsLTI2OCAyMzksLTI3NiAyNzksLTI3NiAzMzUsLTI3NiAzODEsLTI1NCA0MTcsLTIxMSA0NTMsLTE2OCA0ODksLTk3IDUyNiwyIEwgMTM1LDEwNDIgMzA1LDEwNDIgNjAwLDIxMSA4OTUsMTA0MiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJ1IiBob3Jpei1hZHYteD0iODMyIiBkPSJNIDEwMTYsMCBMIDg2MCwwIDg2MCwxNTQgQyA3NDgsMzUgNjMwLC0yNSA1MDYsLTI1IDMxMSwtMjUgMjEzLDkzIDIxMywzMzAgTCAyMTMsMTA0MiAzNjksMTA0MiAzNjksMzc1IEMgMzY5LDI4NiAzODMsMjIwIDQxMSwxNzcgNDM5LDEzMyA0ODYsMTExIDU1MywxMTEgNjQ4LDExMSA3NTEsMTcwIDg2MCwyODkgTCA4NjAsMTA0MiAxMDE2LDEwNDIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0idCIgaG9yaXotYWR2LXg9IjgwOSIgZD0iTSAxMDI4LDQ3IEMgOTIzLC0xIDgyMCwtMjUgNzE5LC0yNSA1MzMsLTI1IDQ0MCw3MyA0NDAsMjcwIEwgNDQwLDkwNyAyNDAsOTA3IDI0MCwxMDQyIDQ0MCwxMDQyIDQ0MCwxMzI3IDU5MiwxMzI3IDU5MiwxMDQyIDk0NiwxMDQyIDk0Niw5MDcgNTkyLDkwNyA1OTIsMzExIEMgNTkyLDI0OCA2MDYsMTk5IDYzNCwxNjQgNjYyLDEyOSA3MDAsMTExIDc0OCwxMTEgODMzLDExMSA5MTgsMTMwIDEwMDEsMTY4IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InMiIGhvcml6LWFkdi14PSI3ODYiIGQ9Ik0gOTA5LDg5MyBDIDgyMiw5MjIgNzM5LDkzNiA2NjIsOTM2IDU3Nyw5MzYgNTEwLDkyMiA0NjEsODk0IDQxMiw4NjUgMzg3LDgyNyAzODcsNzgwIDM4Nyw3NTAgMzk4LDcyNCA0MjEsNzAyIDQ0NCw2NzkgNDg5LDY1OCA1NTcsNjM5IEwgNzA2LDU5NiBDIDc3NSw1NzcgODMxLDU1NCA4NzQsNTI3IDkxNyw1MDAgOTUwLDQ2NyA5NzIsNDI5IDk5MywzOTAgMTAwNCwzNDYgMTAwNCwyOTUgMTAwNCwxOTYgOTYyLDExNyA4NzksNjAgNzk2LDIgNjkwLC0yNyA1NjEsLTI3IDQzMywtMjcgMzI3LC04IDI0NCwzMSBMIDI3NCwxNjYgQyAzNjEsMTI1IDQ1NSwxMDQgNTU1LDEwNCA2NDIsMTA0IDcxMiwxMjAgNzY3LDE1MyA4MjEsMTg1IDg0OCwyMjggODQ4LDI4MSA4NDgsMzE3IDgzNSwzNDkgODA4LDM3OCA3ODEsNDA2IDcxNyw0MzUgNjE3LDQ2NiA1MzYsNDkxIDQ3Nyw1MDkgNDM5LDUyMiA0MDEsNTM0IDM2Niw1NTIgMzMzLDU3NiAzMDAsNjAwIDI3Niw2MjkgMjU5LDY2MiAyNDIsNjk1IDIzMyw3MzMgMjMzLDc3NCAyMzMsODY0IDI3Miw5MzUgMzUxLDk4OCA0MzAsMTA0MSA1MzQsMTA2NyA2NjQsMTA2NyA3NDgsMTA2NyA4NDEsMTA1MyA5NDQsMTAyNCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJyIiBob3Jpei1hZHYteD0iNzE3IiBkPSJNIDEwMzQsMTAxNCBMIDk3MSw4NjQgQyA5MDIsODk0IDg0Myw5MDkgNzkzLDkwOSA3NDcsOTA5IDcwMiw4OTYgNjU4LDg3MCA2MTQsODQzIDU3NCw4MDYgNTM3LDc1NyA1MDAsNzA4IDQ4MSw2ODAgNDgxLDY3NCBMIDQ4MSwwIDMyNiwwIDMyNiwxMDQyIDQ4MSwxMDQyIDQ4MSw4MzEgQyA1NjQsOTg4IDY3MiwxMDY3IDgwNSwxMDY3IDg4NCwxMDY3IDk2MCwxMDQ5IDEwMzQsMTAxNCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJwIiBob3Jpei1hZHYteD0iODc4IiBkPSJNIDM3MSw5MDcgQyA0MDYsOTU1IDQ1Myw5OTQgNTEyLDEwMjMgNTcxLDEwNTIgNjMzLDEwNjcgNjk4LDEwNjcgODE4LDEwNjcgOTEyLDEwMTggOTgxLDkxOSAxMDQ5LDgyMCAxMDgzLDY5MSAxMDgzLDUzMiAxMDgzLDM3MiAxMDQ3LDIzOSA5NzYsMTMzIDkwNCwyNiA4MDcsLTI3IDY4NiwtMjcgNjIxLC0yNyA1NjEsLTExIDUwNSwyMiA0NDgsNTQgNDA0LDk3IDM3MSwxNTAgTCAzNzEsLTQxMiAyMTUsLTQxMiAyMTUsMTA0MiAzNzEsMTA0MiBaIE0gMzcxLDc1MiBMIDM3MSwzMTkgQyA0MDAsMjYwIDQ0MCwyMTEgNDkwLDE3MiA1NDAsMTMzIDU5MiwxMTMgNjQ1LDExMyA3MjksMTEzIDc5NiwxNTIgODQ2LDIzMSA4OTUsMzEwIDkyMCw0MDcgOTIwLDUyNCA5MjAsNjQ0IDg5Niw3NDIgODQ5LDgxOCA4MDIsODk0IDczNiw5MzIgNjUxLDkzMiA1NDEsOTMyIDQ0OCw4NzIgMzcxLDc1MiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJvIiBob3Jpei1hZHYteD0iOTQ3IiBkPSJNIDYxNCwxMDY3IEMgNzU3LDEwNjcgODcxLDEwMTcgOTU2LDkxNyAxMDQxLDgxNyAxMDgzLDY4NSAxMDgzLDUyMiAxMDgzLDM1NyAxMDQxLDIyNSA5NTYsMTI0IDg3MSwyMyA3NTcsLTI3IDYxNCwtMjcgNDcwLC0yNyAzNTYsMjMgMjcyLDEyNCAxODcsMjI0IDE0NSwzNTcgMTQ1LDUyMiAxNDUsNjg2IDE4Nyw4MTggMjcyLDkxOCAzNTcsMTAxNyA0NzEsMTA2NyA2MTQsMTA2NyBaIE0gNjE0LDEwOSBDIDcwOSwxMDkgNzgzLDE0OSA4MzgsMjI5IDg5MywzMDkgOTIwLDQwNiA5MjAsNTIwIDkyMCw2NDAgODkzLDczOSA4MzksODE2IDc4NSw4OTMgNzEwLDkzMiA2MTQsOTMyIDUxNyw5MzIgNDQyLDg5NCAzODksODE4IDMzNiw3NDEgMzA5LDY0MiAzMDksNTIwIDMwOSw0MDQgMzM2LDMwNyAzOTAsMjI4IDQ0NCwxNDkgNTE5LDEwOSA2MTQsMTA5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im4iIGhvcml6LWFkdi14PSI4MzIiIGQ9Ik0gMTAxOCwwIEwgODYyLDAgODYyLDY2OCBDIDg2Miw3NjEgODQ3LDgyOCA4MTgsODcwIDc4OCw5MTEgNzQxLDkzMiA2NzgsOTMyIDU4Myw5MzIgNDgwLDg3MyAzNzEsNzU0IEwgMzcxLDAgMjE1LDAgMjE1LDEwNDIgMzcxLDEwNDIgMzcxLDg4OSBDIDQ4MywxMDA4IDYwMSwxMDY3IDcyNSwxMDY3IDkyMCwxMDY3IDEwMTgsOTQ5IDEwMTgsNzEzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im0iIGhvcml6LWFkdi14PSI5OTQiIGQ9Ik0gMTEwMCwwIEwgOTQ0LDAgOTQ0LDY4NCBDIDk0NCw4NDkgOTEzLDkzMiA4NTIsOTMyIDgyMSw5MzIgNzg2LDkwNCA3NDksODQ3IDcxMSw3OTAgNjkyLDc1OCA2OTIsNzUyIEwgNjkyLDAgNTM3LDAgNTM3LDY4NCBDIDUzNyw4NDkgNTA2LDkzMiA0NDQsOTMyIDQxMyw5MzIgMzc5LDkwNCAzNDIsODQ3IDMwNCw3OTAgMjg1LDc1OCAyODUsNzUyIEwgMjg1LDAgMTI5LDAgMTI5LDEwNDIgMjc5LDEwNDIgMjc5LDg4NSBDIDMwOCw5NDAgMzQzLDk4NSAzODQsMTAxOCA0MjQsMTA1MSA0NjQsMTA2NyA1MDQsMTA2NyA1NDYsMTA2NyA1ODQsMTA1MSA2MTcsMTAxOCA2NTAsOTg1IDY3Myw5NDAgNjg2LDg4NSA3MTUsOTQwIDc1MCw5ODUgNzkxLDEwMTggODMyLDEwNTEgODcyLDEwNjcgOTExLDEwNjcgMTAzNywxMDY3IDExMDAsOTU0IDExMDAsNzI5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImwiIGhvcml6LWFkdi14PSI3MTciIGQ9Ik0gOTQyLDAgTCA1MTYsMCA1MTYsMTI3NiAyNDQsMTI3NiAyNDQsMTQxMSA2NzIsMTQxMSA2NzIsMTM1IDk0MiwxMzUgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iaSIgaG9yaXotYWR2LXg9IjUzMiIgZD0iTSA2ODIsMTQzNiBDIDcxMSwxNDM2IDczNiwxNDI2IDc1NywxNDA1IDc3OCwxMzg0IDc4OCwxMzU4IDc4OCwxMzI5IDc4OCwxMjk4IDc3OCwxMjczIDc1OCwxMjUyIDczNywxMjMxIDcxMiwxMjIxIDY4MiwxMjIxIDY1NSwxMjIxIDYzMSwxMjMyIDYxMCwxMjU0IDU4OSwxMjc1IDU3OCwxMzAwIDU3OCwxMzI5IDU3OCwxMzU4IDU4OSwxMzgzIDYxMCwxNDA0IDYzMSwxNDI1IDY1NSwxNDM2IDY4MiwxNDM2IFogTSA3NTgsMCBMIDYwMiwwIDYwMiw5MDcgMjk1LDkwNyAyOTUsMTA0MiA3NTgsMTA0MiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJnIiBob3Jpei1hZHYteD0iOTQ3IiBkPSJNIDUwNCwzOTMgQyA0MDcsMzQ2IDM1OCwzMDQgMzU4LDI2OCAzNTgsMjQ5IDM2OSwyMzIgMzkyLDIxOSA0MTQsMjA2IDQ0NCwxOTkgNDgzLDE5OSBMIDgzNCwyMDkgQyA5MTcsMjA5IDk4MiwxODkgMTAyOSwxNDggMTA3NSwxMDcgMTA5OCw1MCAxMDk4LC0yNSAxMDk4LC0xNDMgMTA0NSwtMjM3IDk0MCwtMzA3IDgzNSwtMzc3IDcxMiwtNDEyIDU3MSwtNDEyIDQ1NiwtNDEyIDM2MSwtMzg4IDI4OCwtMzQwIDIxNSwtMjkxIDE3OCwtMjI5IDE3OCwtMTUyIDE3OCwtNjkgMjM1LDIgMzQ4LDU5IDI1NCwxMDMgMjA3LDE1NyAyMDcsMjIxIDIwNywyOTQgMjY4LDM2MyAzODksNDI2IDMzMyw0NjAgMjkwLDUwNCAyNjAsNTU4IDIzMCw2MTIgMjE1LDY2OSAyMTUsNzI5IDIxNSw4MjcgMjQ4LDkwOCAzMTUsOTcxIDM4MiwxMDM0IDQ2NSwxMDY1IDU2NSwxMDY1IDYzNywxMDY1IDcwMiwxMDUwIDc2MCwxMDIwIEwgMTA2OSwxMDY3IDEwNjksOTE1IDg3MCw5MTUgQyA5MDAsODYyIDkxNSw4MDAgOTE1LDcyOSA5MTUsNjM0IDg4Miw1NTQgODE2LDQ4OSA3NTAsNDI0IDY2NiwzOTEgNTY1LDM5MSA1NDQsMzkxIDUyMywzOTIgNTA0LDM5MyBaIE0gNTY1LDUxMiBDIDYyMiw1MTIgNjY5LDUzMiA3MDYsNTcyIDc0Miw2MTEgNzYwLDY2MyA3NjAsNzI3IDc2MCw3OTQgNzQyLDg0NyA3MDcsODg1IDY3Miw5MjMgNjI0LDk0MiA1NjUsOTQyIDUxMCw5NDIgNDYzLDkyMiA0MjYsODgzIDM4OCw4NDMgMzY5LDc5MSAzNjksNzI3IDM2OSw2NjQgMzg4LDYxMyA0MjYsNTczIDQ2Myw1MzIgNTEwLDUxMiA1NjUsNTEyIFogTSA1NzEsLTI5MSBDIDY3NCwtMjkxIDc2MSwtMjY4IDgzNCwtMjIzIDkwNiwtMTc3IDk0MiwtMTIwIDk0MiwtNTMgOTQyLDE2IDg5MSw1MSA3ODgsNTEgNzU0LDUxIDY5NSw0OSA2MTIsNDUgNTU5LDQyIDUxOCw0MSA0ODgsNDEgNDczLDQxIDQ1MSwyOSA0MjAsNSAzODksLTE5IDM2NSwtNDMgMzQ5LC02OCAzMzIsLTkyIDMyNCwtMTE3IDMyNCwtMTQzIDMyNCwtMTg0IDM0NywtMjE5IDM5MiwtMjQ4IDQzNywtMjc3IDQ5NywtMjkxIDU3MSwtMjkxIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImYiIGhvcml6LWFkdi14PSI4MzIiIGQ9Ik0gMTAyOCwxMzk1IEwgOTg3LDEyNjggQyA5MTMsMTI4OSA4NDcsMTMwMCA3ODgsMTMwMCA3MzIsMTMwMCA2OTQsMTI4NCA2NzMsMTI1MyA2NTIsMTIyMSA2NDEsMTE1NyA2NDEsMTA2MSBMIDY0MSwxMDQyIDk5MSwxMDQyIDk5MSw5MDcgNjQxLDkwNyA2NDEsMCA0ODUsMCA0ODUsOTA3IDIyMyw5MDcgMjIzLDEwNDIgNDg1LDEwNDIgNDg1LDEwNzcgQyA0ODUsMTIxMCA1MDgsMTMwMyA1NTMsMTM1NiA1OTgsMTQwOSA2NzcsMTQzNiA3ODgsMTQzNiA4NjAsMTQzNiA5NDAsMTQyMiAxMDI4LDEzOTUgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZSIgaG9yaXotYWR2LXg9IjkwMSIgZD0iTSAxMDQ5LDUxNiBMIDMzNCw1MTYgQyAzMzQsMzk4IDM2OCwzMDEgNDM3LDIyNCA1MDUsMTQ3IDU4OSwxMDkgNjg4LDEwOSA3ODMsMTA5IDg4MCwxMjYgOTgxLDE2MCBMIDEwMDgsMzUgQyA5MTYsLTYgODA5LC0yNyA2ODYsLTI3IDUzMSwtMjcgNDA3LDIzIDMxMiwxMjIgMjE3LDIyMSAxNzAsMzU1IDE3MCw1MjQgMTcwLDY4OSAyMTMsODIxIDI5OCw5MjAgMzgzLDEwMTggNDkyLDEwNjcgNjI3LDEwNjcgNzQ3LDEwNjcgODQ3LDEwMjEgOTI4LDkzMCAxMDA5LDgzOSAxMDQ5LDcxNyAxMDQ5LDU2NSBaIE0gODc5LDY0MyBDIDg3OSw3MjQgODU0LDc5MyA4MDMsODQ5IDc1Miw5MDQgNjk2LDkzMiA2MzMsOTMyIDU1Miw5MzIgNDg2LDkwNSA0MzMsODUyIDM4MCw3OTkgMzUwLDcyOSAzNDQsNjQzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImQiIGhvcml6LWFkdi14PSI4NzgiIGQ9Ik0gMTAxNCwwIEwgODU4LDAgODU4LDE1MCBDIDgyNiw5OSA3ODAsNTYgNzIxLDIzIDY2MSwtMTAgNTk4LC0yNyA1MzIsLTI3IDQxMywtMjcgMzE4LDI2IDI0OSwxMzMgMTgwLDIzOSAxNDUsMzc0IDE0NSw1MzcgMTQ1LDY5MCAxODEsODE2IDI1NCw5MTcgMzI2LDEwMTcgNDIyLDEwNjcgNTQxLDEwNjcgNjcyLDEwNjcgNzc4LDEwMTQgODU4LDkwNyBMIDg1OCwxNDExIDEwMTQsMTQxMSBaIE0gODU4LDMxOSBMIDg1OCw3NTIgQyA3ODMsODcyIDY5Myw5MzIgNTg4LDkzMiA1MDIsOTMyIDQzNCw4OTQgMzg0LDgxOCAzMzQsNzQxIDMwOSw2NDUgMzA5LDUyOCAzMDksNDA4IDMzMywzMDkgMzgwLDIzMSA0MjcsMTUyIDQ5MSwxMTMgNTczLDExMyA2MTIsMTEzIDY1MiwxMjQgNjkyLDE0NSA3MzIsMTY2IDc3MCwxOTggODA1LDI0MSA4NDAsMjg0IDg1OCwzMTAgODU4LDMxOSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJjIiBob3Jpei1hZHYteD0iODU1IiBkPSJNIDEwMTQsMzUgQyA5MjMsLTYgODI0LC0yNyA3MTUsLTI3IDU1MSwtMjcgNDIwLDIzIDMyMywxMjMgMjI1LDIyMiAxNzYsMzU1IDE3Niw1MjAgMTc2LDY4NSAyMjUsODE4IDMyNCw5MTggNDIyLDEwMTcgNTUxLDEwNjcgNzExLDEwNjcgODE2LDEwNjcgOTE1LDEwNDkgMTAxMCwxMDEyIEwgOTY5LDg4MyBDIDg3NCw5MTYgNzg5LDkzMiA3MTMsOTMyIDU5OCw5MzIgNTA4LDg5NSA0NDEsODIxIDM3NCw3NDcgMzQwLDY0NyAzNDAsNTIwIDM0MCw0MDQgMzc1LDMwNyA0NDQsMjI4IDUxMywxNDkgNjA0LDEwOSA3MTUsMTA5IDgwMywxMDkgODk0LDEyNiA5ODcsMTYwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImEiIGhvcml6LWFkdi14PSI4NTUiIGQ9Ik0gMTA0NCwwIEwgODg5LDAgQyA4NzYsNDIgODcwLDk4IDg3MCwxNjggNzgxLDM4IDY2MSwtMjcgNTA4LC0yNyA0MzEsLTI3IDM2MywxIDMwNCw1NiAyNDUsMTExIDIxNSwxODEgMjE1LDI2OCAyMTUsMzE5IDIyNSwzNjUgMjQ2LDQwNCAyNjcsNDQzIDI5Nyw0NzcgMzM3LDUwNCAzNzcsNTMxIDQyNiw1NTIgNDgzLDU2OCA1NDAsNTgzIDYwOCw1OTQgNjg3LDYwMCBMIDg2Miw2MTQgODYyLDY2NCBDIDg2Miw4NDMgNzc5LDkzMiA2MTIsOTMyIDU3Myw5MzIgNTIzLDkyNCA0NjMsOTA3IDQwMiw4OTAgMzUwLDg2OSAzMDcsODQ2IEwgMjY2LDk1OCBDIDQwOCwxMDMxIDU0MCwxMDY3IDY2MiwxMDY3IDg5OSwxMDY3IDEwMTgsOTQxIDEwMTgsNjkwIEwgMTAxOCwyNDIgQyAxMDE4LDE1MSAxMDI3LDcxIDEwNDQsMCBaIE0gODYyLDMwOSBMIDg2Miw0OTggQyA3MjEsNDg1IDYyNSw0NzQgNTczLDQ2MyA1MjAsNDUyIDQ3NSw0MzEgNDM3LDQwMSAzOTgsMzcxIDM3OSwzMzAgMzc5LDI3OSAzNzksMjMyIDM5NSwxOTUgNDI4LDE2NiA0NjEsMTM3IDUwMiwxMjMgNTUzLDEyMyA2MDgsMTIzIDY2MywxNDAgNzE4LDE3MyA3NzMsMjA2IDgyMSwyNTEgODYyLDMwOSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSIuIiBob3Jpei1hZHYteD0iMjU1IiBkPSJNIDYwOCwyMjkgQyA2NDMsMjI5IDY3MywyMTcgNjk3LDE5MyA3MjEsMTY5IDczMywxMzkgNzMzLDEwNCA3MzMsNjggNzIxLDM3IDY5NywxMiA2NzIsLTE0IDY0MywtMjcgNjA4LC0yNyA1NzQsLTI3IDU0NSwtMTQgNTIxLDEyIDQ5NywzNyA0ODUsNjggNDg1LDEwNCA0ODUsMTQwIDQ5NywxNzAgNTIxLDE5NCA1NDQsMjE3IDU3MywyMjkgNjA4LDIyOSBaIi8+CiAgPC9mb250PgogPC9kZWZzPgogPGRlZnM+CiAgPGZvbnQgaWQ9IkVtYmVkZGVkRm9udF8yIiBob3Jpei1hZHYteD0iMjA0OCI+CiAgIDxmb250LWZhY2UgZm9udC1mYW1pbHk9IkFyaWFsIGVtYmVkZGVkIiB1bml0cy1wZXItZW09IjIwNDgiIGZvbnQtd2VpZ2h0PSJub3JtYWwiIGZvbnQtc3R5bGU9Im5vcm1hbCIgYXNjZW50PSIxODcwIiBkZXNjZW50PSI0MzkiLz4KICAgPG1pc3NpbmctZ2x5cGggaG9yaXotYWR2LXg9IjIwNDgiIGQ9Ik0gMCwwIEwgMjA0NywwIDIwNDcsMjA0NyAwLDIwNDcgMCwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InkiIGhvcml6LWFkdi14PSI5OTQiIGQ9Ik0gMTI3LC00MDkgTCAxMDcsLTI0MCBDIDE0NiwtMjUxIDE4MSwtMjU2IDIxMCwtMjU2IDI1MCwtMjU2IDI4MiwtMjQ5IDMwNiwtMjM2IDMzMCwtMjIzIDM1MCwtMjA0IDM2NSwtMTgwIDM3NiwtMTYyIDM5NSwtMTE3IDQyMCwtNDYgNDIzLC0zNiA0MjksLTIxIDQzNiwtMiBMIDMzLDEwNjIgMjI3LDEwNjIgNDQ4LDQ0NyBDIDQ3NywzNjkgNTAyLDI4NyA1MjUsMjAxIDU0NiwyODQgNTcwLDM2NCA1OTksNDQzIEwgODI2LDEwNjIgMTAwNiwxMDYyIDYwMiwtMTggQyA1NTksLTEzNSA1MjUsLTIxNSA1MDEsLTI1OSA0NjksLTMxOCA0MzIsLTM2MiAzOTEsLTM5MCAzNTAsLTQxNyAzMDAsLTQzMSAyNDMsLTQzMSAyMDgsLTQzMSAxNzAsLTQyNCAxMjcsLTQwOSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJ2IiBob3Jpei1hZHYteD0iOTk0IiBkPSJNIDQzMCwwIEwgMjYsMTA2MiAyMTYsMTA2MiA0NDQsNDI2IEMgNDY5LDM1NyA0OTEsMjg2IDUxMiwyMTIgNTI4LDI2OCA1NTAsMzM1IDU3OSw0MTQgTCA4MTUsMTA2MiAxMDAwLDEwNjIgNTk4LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0idCIgaG9yaXotYWR2LXg9IjUzMiIgZD0iTSA1MjgsMTYxIEwgNTU0LDIgQyA1MDMsLTkgNDU4LC0xNCA0MTgsLTE0IDM1MywtMTQgMzAyLC00IDI2NiwxNyAyMzAsMzggMjA1LDY1IDE5MCw5OSAxNzUsMTMyIDE2OCwyMDMgMTY4LDMxMSBMIDE2OCw5MjIgMzYsOTIyIDM2LDEwNjIgMTY4LDEwNjIgMTY4LDEzMjUgMzQ3LDE0MzMgMzQ3LDEwNjIgNTI4LDEwNjIgNTI4LDkyMiAzNDcsOTIyIDM0NywzMDEgQyAzNDcsMjUwIDM1MCwyMTcgMzU3LDIwMiAzNjMsMTg3IDM3MywxNzYgMzg4LDE2NyA0MDIsMTU4IDQyMiwxNTQgNDQ5LDE1NCA0NjksMTU0IDQ5NSwxNTYgNTI4LDE2MSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJzIiBob3Jpei1hZHYteD0iOTAyIiBkPSJNIDYzLDMxNyBMIDI0MSwzNDUgQyAyNTEsMjc0IDI3OSwyMTkgMzI1LDE4MSAzNzAsMTQzIDQzNCwxMjQgNTE2LDEyNCA1OTksMTI0IDY2MCwxNDEgNzAwLDE3NSA3NDAsMjA4IDc2MCwyNDggNzYwLDI5MyA3NjAsMzM0IDc0MiwzNjYgNzA3LDM4OSA2ODIsNDA1IDYyMSw0MjUgNTIzLDQ1MCAzOTEsNDgzIDMwMCw1MTIgMjQ5LDUzNyAxOTgsNTYxIDE1OSw1OTUgMTMzLDYzOCAxMDYsNjgxIDkzLDcyOCA5Myw3ODAgOTMsODI3IDEwNCw4NzEgMTI2LDkxMiAxNDcsOTUyIDE3Nyw5ODUgMjE0LDEwMTIgMjQyLDEwMzMgMjgwLDEwNTAgMzI5LDEwNjUgMzc3LDEwNzkgNDI5LDEwODYgNDg0LDEwODYgNTY3LDEwODYgNjQxLDEwNzQgNzA0LDEwNTAgNzY3LDEwMjYgODEzLDk5NCA4NDMsOTUzIDg3Myw5MTIgODk0LDg1NyA5MDUsNzg4IEwgNzI5LDc2NCBDIDcyMSw4MTkgNjk4LDg2MSA2NjAsODkyIDYyMSw5MjMgNTY3LDkzOCA0OTcsOTM4IDQxNCw5MzggMzU1LDkyNCAzMjAsODk3IDI4NSw4NzAgMjY3LDgzOCAyNjcsODAxIDI2Nyw3NzggMjc0LDc1NyAyODksNzM4IDMwNCw3MTkgMzI3LDcwMyAzNTgsNjkwIDM3Niw2ODMgNDI5LDY2OCA1MTcsNjQ0IDY0NCw2MTAgNzMzLDU4MiA3ODQsNTYxIDgzNCw1MzkgODczLDUwNyA5MDIsNDY2IDkzMSw0MjUgOTQ1LDM3MyA5NDUsMzEyIDk0NSwyNTIgOTI4LDE5NiA4OTMsMTQzIDg1OCw5MCA4MDcsNDkgNzQxLDIwIDY3NSwtMTAgNjAwLC0yNCA1MTcsLTI0IDM3OSwtMjQgMjc0LDUgMjAyLDYyIDEyOSwxMTkgODMsMjA0IDYzLDMxNyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJwIiBob3Jpei1hZHYteD0iOTQ4IiBkPSJNIDEzNSwtNDA3IEwgMTM1LDEwNjIgMjk5LDEwNjIgMjk5LDkyNCBDIDMzOCw5NzggMzgxLDEwMTkgNDMwLDEwNDYgNDc5LDEwNzMgNTM4LDEwODYgNjA3LDEwODYgNjk4LDEwODYgNzc4LDEwNjMgODQ3LDEwMTYgOTE2LDk2OSA5NjksOTA0IDEwMDQsODE5IDEwMzksNzM0IDEwNTcsNjQwIDEwNTcsNTM5IDEwNTcsNDMwIDEwMzgsMzMzIDk5OSwyNDYgOTYwLDE1OSA5MDMsOTIgODI5LDQ2IDc1NCwtMSA2NzYsLTI0IDU5NCwtMjQgNTM0LC0yNCA0ODAsLTExIDQzMywxNCAzODUsMzkgMzQ2LDcxIDMxNSwxMTAgTCAzMTUsLTQwNyBaIE0gMjk4LDUyNSBDIDI5OCwzODggMzI2LDI4NyAzODEsMjIyIDQzNiwxNTcgNTAzLDEyNCA1ODIsMTI0IDY2MiwxMjQgNzMxLDE1OCA3ODgsMjI2IDg0NSwyOTMgODczLDM5OCA4NzMsNTQwIDg3Myw2NzUgODQ1LDc3NyA3OTAsODQ0IDczNCw5MTEgNjY3LDk0NSA1OTAsOTQ1IDUxMyw5NDUgNDQ2LDkwOSAzODcsODM4IDMyOCw3NjYgMjk4LDY2MiAyOTgsNTI1IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im8iIGhvcml6LWFkdi14PSIxMDQwIiBkPSJNIDY4LDUzMSBDIDY4LDcyOCAxMjMsODczIDIzMiw5NjggMzIzLDEwNDcgNDM1LDEwODYgNTY2LDEwODYgNzEyLDEwODYgODMxLDEwMzggOTI0LDk0MyAxMDE3LDg0NyAxMDYzLDcxNSAxMDYzLDU0NiAxMDYzLDQwOSAxMDQzLDMwMiAxMDAyLDIyNCA5NjEsMTQ1IDkwMSw4NCA4MjMsNDEgNzQ0LC0yIDY1OSwtMjQgNTY2LC0yNCA0MTcsLTI0IDI5NywyNCAyMDYsMTE5IDExNCwyMTQgNjgsMzUyIDY4LDUzMSBaIE0gMjUzLDUzMSBDIDI1MywzOTUgMjgzLDI5MyAzNDIsMjI2IDQwMSwxNTggNDc2LDEyNCA1NjYsMTI0IDY1NSwxMjQgNzMwLDE1OCA3ODksMjI2IDg0OCwyOTQgODc4LDM5OCA4NzgsNTM3IDg3OCw2NjggODQ4LDc2OCA3ODksODM2IDcyOSw5MDMgNjU1LDkzNyA1NjYsOTM3IDQ3Niw5MzcgNDAxLDkwMyAzNDIsODM2IDI4Myw3NjkgMjUzLDY2NyAyNTMsNTMxIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im4iIGhvcml6LWFkdi14PSI5MDIiIGQ9Ik0gMTM1LDAgTCAxMzUsMTA2MiAyOTcsMTA2MiAyOTcsOTExIEMgMzc1LDEwMjggNDg4LDEwODYgNjM1LDEwODYgNjk5LDEwODYgNzU4LDEwNzUgODEyLDEwNTIgODY1LDEwMjkgOTA1LDk5OCA5MzIsOTYxIDk1OSw5MjQgOTc3LDg3OSA5ODgsODI4IDk5NSw3OTUgOTk4LDczNiA5OTgsNjUzIEwgOTk4LDAgODE4LDAgODE4LDY0NiBDIDgxOCw3MTkgODExLDc3NCA3OTcsODExIDc4Myw4NDcgNzU4LDg3NiA3MjMsODk4IDY4Nyw5MTkgNjQ1LDkzMCA1OTcsOTMwIDUyMCw5MzAgNDU0LDkwNiAzOTksODU3IDM0Myw4MDggMzE1LDcxNiAzMTUsNTgwIEwgMzE1LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iayIgaG9yaXotYWR2LXg9IjkyNSIgZD0iTSAxMzYsMCBMIDEzNiwxNDY2IDMxNiwxNDY2IDMxNiw2MzAgNzQyLDEwNjIgOTc1LDEwNjIgNTY5LDY2OCAxMDE2LDAgNzk0LDAgNDQzLDU0MyAzMTYsNDIxIDMxNiwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImgiIGhvcml6LWFkdi14PSI5MDIiIGQ9Ik0gMTM1LDAgTCAxMzUsMTQ2NiAzMTUsMTQ2NiAzMTUsOTQwIEMgMzk5LDEwMzcgNTA1LDEwODYgNjMzLDEwODYgNzEyLDEwODYgNzgwLDEwNzEgODM4LDEwNDAgODk2LDEwMDkgOTM4LDk2NiA5NjMsOTExIDk4OCw4NTYgMTAwMCw3NzcgMTAwMCw2NzMgTCAxMDAwLDAgODIwLDAgODIwLDY3MyBDIDgyMCw3NjMgODAxLDgyOSA3NjIsODcwIDcyMyw5MTEgNjY3LDkzMSA1OTYsOTMxIDU0Myw5MzEgNDkzLDkxNyA0NDYsODkwIDM5OSw4NjIgMzY1LDgyNCAzNDUsNzc3IDMyNSw3MzAgMzE1LDY2NCAzMTUsNTgxIEwgMzE1LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZyIgaG9yaXotYWR2LXg9Ijk3MSIgZD0iTSAxMDIsLTg4IEwgMjc3LC0xMTQgQyAyODQsLTE2OCAzMDUsLTIwNyAzMzgsLTIzMiAzODMsLTI2NSA0NDQsLTI4MiA1MjEsLTI4MiA2MDQsLTI4MiA2NjksLTI2NSA3MTQsLTIzMiA3NTksLTE5OSA3OTAsLTE1MiA4MDYsLTkyIDgxNSwtNTUgODIwLDIyIDgxOSwxMzkgNzQwLDQ2IDY0MiwwIDUyNSwwIDM3OSwwIDI2Niw1MyAxODYsMTU4IDEwNiwyNjMgNjYsMzkwIDY2LDUzNyA2Niw2MzggODQsNzMyIDEyMSw4MTggMTU4LDkwMyAyMTEsOTY5IDI4MSwxMDE2IDM1MCwxMDYzIDQzMiwxMDg2IDUyNiwxMDg2IDY1MSwxMDg2IDc1NSwxMDM1IDgzNiw5MzQgTCA4MzYsMTA2MiAxMDAyLDEwNjIgMTAwMiwxNDQgQyAxMDAyLC0yMSA5ODUsLTEzOSA5NTIsLTIwOCA5MTgsLTI3NyA4NjUsLTMzMSA3OTIsLTM3MSA3MTksLTQxMSA2MjksLTQzMSA1MjIsLTQzMSAzOTUsLTQzMSAyOTMsLTQwMyAyMTUsLTM0NiAxMzcsLTI4OSA5OSwtMjAzIDEwMiwtODggWiBNIDI1MSw1NTAgQyAyNTEsNDExIDI3OSwzMDkgMzM0LDI0NSAzODksMTgxIDQ1OSwxNDkgNTQyLDE0OSA2MjUsMTQ5IDY5NCwxODEgNzUwLDI0NSA4MDYsMzA4IDgzNCw0MDggODM0LDU0NCA4MzQsNjc0IDgwNSw3NzIgNzQ4LDgzOCA2OTAsOTA0IDYyMCw5MzcgNTM5LDkzNyA0NTksOTM3IDM5MSw5MDUgMzM1LDg0MCAyNzksNzc1IDI1MSw2NzggMjUxLDU1MCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJlIiBob3Jpei1hZHYteD0iOTk0IiBkPSJNIDg2MiwzNDIgTCAxMDQ4LDMxOSBDIDEwMTksMjEwIDk2NCwxMjYgODg1LDY2IDgwNiw2IDcwNCwtMjQgNTgxLC0yNCA0MjYsLTI0IDMwMywyNCAyMTIsMTIwIDEyMSwyMTUgNzUsMzQ5IDc1LDUyMiA3NSw3MDEgMTIxLDgzOSAyMTMsOTM4IDMwNSwxMDM3IDQyNCwxMDg2IDU3MSwxMDg2IDcxMywxMDg2IDgyOSwxMDM4IDkxOSw5NDEgMTAwOSw4NDQgMTA1NCw3MDggMTA1NCw1MzMgMTA1NCw1MjIgMTA1NCw1MDYgMTA1Myw0ODUgTCAyNjEsNDg1IEMgMjY4LDM2OCAzMDEsMjc5IDM2MCwyMTcgNDE5LDE1NSA0OTMsMTI0IDU4MiwxMjQgNjQ4LDEyNCA3MDQsMTQxIDc1MSwxNzYgNzk4LDIxMSA4MzUsMjY2IDg2MiwzNDIgWiBNIDI3MSw2MzMgTCA4NjQsNjMzIEMgODU2LDcyMiA4MzMsNzg5IDc5Niw4MzQgNzM5LDkwMyA2NjQsOTM4IDU3Myw5MzggNDkwLDkzOCA0MjEsOTEwIDM2NSw4NTUgMzA4LDgwMCAyNzcsNzI2IDI3MSw2MzMgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iYyIgaG9yaXotYWR2LXg9Ijk0OCIgZD0iTSA4MjgsMzg5IEwgMTAwNSwzNjYgQyA5ODYsMjQ0IDkzNiwxNDkgODU3LDgwIDc3NywxMSA2NzksLTI0IDU2MywtMjQgNDE4LC0yNCAzMDEsMjQgMjEzLDExOSAxMjQsMjE0IDgwLDM1MCA4MCw1MjcgODAsNjQyIDk5LDc0MiAxMzcsODI4IDE3NSw5MTQgMjMzLDk3OSAzMTEsMTAyMiAzODgsMTA2NSA0NzMsMTA4NiA1NjQsMTA4NiA2NzksMTA4NiA3NzQsMTA1NyA4NDcsOTk5IDkyMCw5NDAgOTY3LDg1NyA5ODgsNzUwIEwgODEzLDcyMyBDIDc5Niw3OTQgNzY3LDg0OCA3MjUsODg0IDY4Miw5MjAgNjMxLDkzOCA1NzEsOTM4IDQ4MCw5MzggNDA3LDkwNiAzNTAsODQxIDI5Myw3NzYgMjY1LDY3MyAyNjUsNTMyIDI2NSwzODkgMjkyLDI4NiAzNDcsMjIxIDQwMiwxNTYgNDczLDEyNCA1NjEsMTI0IDYzMiwxMjQgNjkxLDE0NiA3MzgsMTg5IDc4NSwyMzIgODE1LDI5OSA4MjgsMzg5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImEiIGhvcml6LWFkdi14PSI5OTQiIGQ9Ik0gODI4LDEzMSBDIDc2MSw3NCA2OTcsMzQgNjM2LDExIDU3NCwtMTIgNTA4LC0yNCA0MzcsLTI0IDMyMCwtMjQgMjMxLDUgMTY4LDYyIDEwNSwxMTkgNzQsMTkxIDc0LDI4MCA3NCwzMzIgODYsMzgwIDExMCw0MjMgMTMzLDQ2NiAxNjQsNTAwIDIwMyw1MjYgMjQxLDU1MiAyODQsNTcyIDMzMiw1ODUgMzY3LDU5NCA0MjEsNjAzIDQ5Miw2MTIgNjM3LDYyOSA3NDQsNjUwIDgxMyw2NzQgODE0LDY5OSA4MTQsNzE0IDgxNCw3MjEgODE0LDc5NCA3OTcsODQ2IDc2Myw4NzYgNzE3LDkxNyA2NDksOTM3IDU1OCw5MzcgNDczLDkzNyA0MTEsOTIyIDM3MSw4OTMgMzMwLDg2MyAzMDAsODEwIDI4MSw3MzUgTCAxMDUsNzU5IEMgMTIxLDgzNCAxNDcsODk1IDE4NCw5NDIgMjIxLDk4OCAyNzQsMTAyNCAzNDMsMTA0OSA0MTIsMTA3NCA0OTMsMTA4NiA1ODQsMTA4NiA2NzUsMTA4NiA3NDgsMTA3NSA4MDUsMTA1NCA4NjIsMTAzMyA5MDMsMTAwNiA5MzAsOTc0IDk1Nyw5NDEgOTc1LDkwMCA5ODYsODUxIDk5Miw4MjAgOTk1LDc2NSA5OTUsNjg1IEwgOTk1LDQ0NSBDIDk5NSwyNzggOTk5LDE3MiAxMDA3LDEyOCAxMDE0LDgzIDEwMjksNDEgMTA1MiwwIEwgODY0LDAgQyA4NDUsMzcgODMzLDgxIDgyOCwxMzEgWiBNIDgxMyw1MzMgQyA3NDgsNTA2IDY1MCw0ODQgNTE5LDQ2NSA0NDUsNDU0IDM5Myw0NDIgMzYyLDQyOSAzMzEsNDE2IDMwOCwzOTYgMjkxLDM3MSAyNzQsMzQ1IDI2NiwzMTYgMjY2LDI4NSAyNjYsMjM3IDI4NCwxOTcgMzIxLDE2NSAzNTcsMTMzIDQxMCwxMTcgNDgwLDExNyA1NDksMTE3IDYxMSwxMzIgNjY1LDE2MyA3MTksMTkzIDc1OSwyMzQgNzg0LDI4NyA4MDMsMzI4IDgxMywzODggODEzLDQ2NyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJTIiBob3Jpei1hZHYteD0iMTIwMiIgZD0iTSA5Miw0NzEgTCAyNzUsNDg3IEMgMjg0LDQxNCAzMDQsMzU0IDMzNiwzMDcgMzY3LDI2MCA0MTYsMjIyIDQ4MywxOTMgNTUwLDE2NCA2MjUsMTQ5IDcwOCwxNDkgNzgyLDE0OSA4NDcsMTYwIDkwNCwxODIgOTYxLDIwNCAxMDAzLDIzNCAxMDMxLDI3MyAxMDU4LDMxMSAxMDcyLDM1MyAxMDcyLDM5OCAxMDcyLDQ0NCAxMDU5LDQ4NCAxMDMyLDUxOSAxMDA1LDU1MyA5NjEsNTgyIDkwMCw2MDUgODYxLDYyMCA3NzQsNjQ0IDYzOSw2NzcgNTA0LDcwOSA0MTAsNzM5IDM1Niw3NjggMjg2LDgwNSAyMzQsODUwIDIwMCw5MDUgMTY1LDk1OSAxNDgsMTAyMCAxNDgsMTA4NyAxNDgsMTE2MSAxNjksMTIzMCAyMTEsMTI5NSAyNTMsMTM1OSAzMTQsMTQwOCAzOTUsMTQ0MSA0NzYsMTQ3NCA1NjUsMTQ5MSA2NjQsMTQ5MSA3NzMsMTQ5MSA4NjksMTQ3NCA5NTIsMTQzOSAxMDM1LDE0MDQgMTA5OCwxMzUyIDExNDMsMTI4NCAxMTg4LDEyMTYgMTIxMiwxMTM5IDEyMTUsMTA1MyBMIDEwMjksMTAzOSBDIDEwMTksMTEzMiA5ODUsMTIwMiA5MjgsMTI0OSA4NzAsMTI5NiA3ODUsMTMyMCA2NzIsMTMyMCA1NTUsMTMyMCA0NjksMTI5OSA0MTYsMTI1NiAzNjIsMTIxMyAzMzUsMTE2MSAzMzUsMTEwMCAzMzUsMTA0NyAzNTQsMTAwNCAzOTIsOTcwIDQyOSw5MzYgNTI3LDkwMSA2ODUsODY2IDg0Miw4MzAgOTUwLDc5OSAxMDA5LDc3MiAxMDk0LDczMyAxMTU3LDY4MyAxMTk4LDYyMyAxMjM5LDU2MiAxMjU5LDQ5MyAxMjU5LDQxNCAxMjU5LDMzNiAxMjM3LDI2MyAxMTkyLDE5NCAxMTQ3LDEyNSAxMDgzLDcxIDEwMDAsMzMgOTE2LC02IDgyMiwtMjUgNzE3LC0yNSA1ODQsLTI1IDQ3MywtNiAzODQsMzMgMjk0LDcyIDIyNCwxMzAgMTczLDIwOCAxMjIsMjg1IDk1LDM3MyA5Miw0NzEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iUCIgaG9yaXotYWR2LXg9IjExNTUiIGQ9Ik0gMTU4LDAgTCAxNTgsMTQ2NiA3MTEsMTQ2NiBDIDgwOCwxNDY2IDg4MywxNDYxIDkzNCwxNDUyIDEwMDYsMTQ0MCAxMDY2LDE0MTcgMTExNSwxMzg0IDExNjQsMTM1MCAxMjAzLDEzMDMgMTIzMywxMjQyIDEyNjIsMTE4MSAxMjc3LDExMTUgMTI3NywxMDQyIDEyNzcsOTE3IDEyMzcsODEyIDExNTgsNzI2IDEwNzksNjM5IDkzNSw1OTYgNzI4LDU5NiBMIDM1Miw1OTYgMzUyLDAgWiBNIDM1Miw3NjkgTCA3MzEsNzY5IEMgODU2LDc2OSA5NDUsNzkyIDk5OCw4MzkgMTA1MSw4ODYgMTA3Nyw5NTEgMTA3NywxMDM2IDEwNzcsMTA5NyAxMDYyLDExNTAgMTAzMSwxMTk0IDEwMDAsMTIzNyA5NTksMTI2NiA5MDgsMTI4MCA4NzUsMTI4OSA4MTUsMTI5MyA3MjcsMTI5MyBMIDM1MiwxMjkzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Ik8iIGhvcml6LWFkdi14PSIxNDMzIiBkPSJNIDk5LDcxNCBDIDk5LDk1NyAxNjQsMTE0OCAyOTUsMTI4NiA0MjYsMTQyMyA1OTQsMTQ5MiA4MDEsMTQ5MiA5MzYsMTQ5MiAxMDU4LDE0NjAgMTE2NywxMzk1IDEyNzYsMTMzMCAxMzU5LDEyNDAgMTQxNiwxMTI1IDE0NzMsMTAwOSAxNTAxLDg3OCAxNTAxLDczMSAxNTAxLDU4MiAxNDcxLDQ0OSAxNDExLDMzMiAxMzUxLDIxNSAxMjY2LDEyNiAxMTU2LDY2IDEwNDYsNSA5MjcsLTI1IDgwMCwtMjUgNjYyLC0yNSA1MzksOCA0MzAsNzUgMzIxLDE0MiAyMzksMjMzIDE4MywzNDggMTI3LDQ2MyA5OSw1ODUgOTksNzE0IFogTSAyOTksNzExIEMgMjk5LDUzNCAzNDcsMzk1IDQ0MiwyOTQgNTM3LDE5MiA2NTYsMTQxIDc5OSwxNDEgOTQ1LDE0MSAxMDY1LDE5MiAxMTYwLDI5NSAxMjU0LDM5OCAxMzAxLDU0MyAxMzAxLDczMiAxMzAxLDg1MSAxMjgxLDk1NiAxMjQxLDEwNDUgMTIwMCwxMTM0IDExNDEsMTIwMyAxMDY0LDEyNTIgOTg2LDEzMDEgODk5LDEzMjUgODAyLDEzMjUgNjY1LDEzMjUgNTQ3LDEyNzggNDQ4LDExODQgMzQ5LDEwODkgMjk5LDkzMiAyOTksNzExIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IkoiIGhvcml6LWFkdi14PSI4MzIiIGQ9Ik0gNTksNDE2IEwgMjM0LDQ0MCBDIDIzOSwzMjggMjYwLDI1MSAyOTcsMjEwIDMzNCwxNjkgMzg2LDE0OCA0NTIsMTQ4IDUwMSwxNDggNTQzLDE1OSA1NzgsMTgyIDYxMywyMDQgNjM4LDIzNCA2NTEsMjczIDY2NCwzMTEgNjcxLDM3MiA2NzEsNDU2IEwgNjcxLDE0NjYgODY1LDE0NjYgODY1LDQ2NyBDIDg2NSwzNDQgODUwLDI0OSA4MjEsMTgyIDc5MSwxMTUgNzQ0LDYzIDY4MCwyOCA2MTUsLTcgNTQwLC0yNSA0NTMsLTI1IDMyNCwtMjUgMjI2LDEyIDE1OCw4NiA4OSwxNjAgNTYsMjcwIDU5LDQxNiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJJIiBob3Jpei1hZHYteD0iMjA5IiBkPSJNIDE5MSwwIEwgMTkxLDE0NjYgMzg1LDE0NjYgMzg1LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iRyIgaG9yaXotYWR2LXg9IjEzODciIGQ9Ik0gODQ0LDU3NSBMIDg0NCw3NDcgMTQ2NSw3NDggMTQ2NSwyMDQgQyAxMzcwLDEyOCAxMjcxLDcxIDExNzAsMzMgMTA2OSwtNiA5NjUsLTI1IDg1OCwtMjUgNzE0LC0yNSA1ODMsNiA0NjYsNjggMzQ4LDEyOSAyNTksMjE4IDE5OSwzMzUgMTM5LDQ1MiAxMDksNTgyIDEwOSw3MjYgMTA5LDg2OSAxMzksMTAwMiAxOTksMTEyNiAyNTgsMTI0OSAzNDQsMTM0MSA0NTYsMTQwMSA1NjgsMTQ2MSA2OTcsMTQ5MSA4NDMsMTQ5MSA5NDksMTQ5MSAxMDQ1LDE0NzQgMTEzMSwxNDQwIDEyMTYsMTQwNSAxMjgzLDEzNTcgMTMzMiwxMjk2IDEzODEsMTIzNSAxNDE4LDExNTUgMTQ0MywxMDU2IEwgMTI2OCwxMDA4IEMgMTI0NiwxMDgzIDEyMTksMTE0MSAxMTg2LDExODQgMTE1MywxMjI3IDExMDcsMTI2MSAxMDQ2LDEyODcgOTg1LDEzMTIgOTE4LDEzMjUgODQ0LDEzMjUgNzU1LDEzMjUgNjc5LDEzMTIgNjE0LDEyODUgNTQ5LDEyNTggNDk3LDEyMjIgNDU4LDExNzggNDE4LDExMzQgMzg3LDEwODYgMzY1LDEwMzMgMzI4LDk0MiAzMDksODQ0IDMwOSw3MzggMzA5LDYwNyAzMzIsNDk4IDM3Nyw0MTAgNDIyLDMyMiA0ODcsMjU3IDU3MywyMTQgNjU5LDE3MSA3NTAsMTUwIDg0NywxNTAgOTMxLDE1MCAxMDEzLDE2NiAxMDkzLDE5OSAxMTczLDIzMSAxMjM0LDI2NSAxMjc1LDMwMiBMIDEyNzUsNTc1IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IkMiIGhvcml6LWFkdi14PSIxMzE4IiBkPSJNIDEyMDQsNTE0IEwgMTM5OCw0NjUgQyAxMzU3LDMwNiAxMjg0LDE4NCAxMTc5LDEwMSAxMDczLDE3IDk0NCwtMjUgNzkxLC0yNSA2MzMsLTI1IDUwNSw3IDQwNiw3MiAzMDcsMTM2IDIzMSwyMjkgMTgwLDM1MSAxMjgsNDczIDEwMiw2MDQgMTAyLDc0NCAxMDIsODk3IDEzMSwxMDMwIDE5MCwxMTQ0IDI0OCwxMjU3IDMzMSwxMzQ0IDQzOSwxNDAzIDU0NiwxNDYyIDY2NSwxNDkxIDc5NCwxNDkxIDk0MSwxNDkxIDEwNjQsMTQ1NCAxMTY0LDEzNzkgMTI2NCwxMzA0IDEzMzQsMTE5OSAxMzczLDEwNjQgTCAxMTgyLDEwMTkgQyAxMTQ4LDExMjYgMTA5OSwxMjAzIDEwMzQsMTI1MiA5NjksMTMwMSA4ODgsMTMyNSA3OTAsMTMyNSA2NzcsMTMyNSA1ODMsMTI5OCA1MDgsMTI0NCA0MzIsMTE5MCAzNzksMTExOCAzNDgsMTAyNyAzMTcsOTM2IDMwMiw4NDIgMzAyLDc0NSAzMDIsNjIwIDMyMCw1MTIgMzU3LDQxOSAzOTMsMzI2IDQ0OSwyNTYgNTI2LDIxMCA2MDMsMTY0IDY4NiwxNDEgNzc1LDE0MSA4ODQsMTQxIDk3NiwxNzIgMTA1MSwyMzUgMTEyNiwyOTggMTE3NywzOTEgMTIwNCw1MTQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iOSIgaG9yaXotYWR2LXg9Ijk5NCIgZD0iTSAxMTIsMzM5IEwgMjg1LDM1NSBDIDMwMCwyNzQgMzI4LDIxNSAzNjksMTc4IDQxMCwxNDEgNDYzLDEyMyA1MjgsMTIzIDU4MywxMjMgNjMyLDEzNiA2NzQsMTYxIDcxNSwxODYgNzQ5LDIyMCA3NzYsMjYzIDgwMywzMDUgODI1LDM2MiA4NDMsNDM0IDg2MSw1MDYgODcwLDU3OSA4NzAsNjU0IDg3MCw2NjIgODcwLDY3NCA4NjksNjkwIDgzMyw2MzMgNzg0LDU4NiA3MjIsNTUxIDY1OSw1MTUgNTkyLDQ5NyA1MTksNDk3IDM5OCw0OTcgMjk1LDU0MSAyMTEsNjI5IDEyNyw3MTcgODUsODMzIDg1LDk3NyA4NSwxMTI2IDEyOSwxMjQ1IDIxNywxMzM2IDMwNCwxNDI3IDQxNCwxNDcyIDU0NiwxNDcyIDY0MSwxNDcyIDcyOSwxNDQ2IDgwOCwxMzk1IDg4NywxMzQ0IDk0NywxMjcxIDk4OCwxMTc2IDEwMjksMTA4MSAxMDQ5LDk0MyAxMDQ5LDc2MyAxMDQ5LDU3NiAxMDI5LDQyNyA5ODgsMzE2IDk0NywyMDUgODg3LDEyMCA4MDcsNjIgNzI2LDQgNjMyLC0yNSA1MjQsLTI1IDQwOSwtMjUgMzE2LDcgMjQzLDcxIDE3MCwxMzQgMTI3LDIyNCAxMTIsMzM5IFogTSA4NDksOTg2IEMgODQ5LDEwODkgODIyLDExNzEgNzY3LDEyMzIgNzEyLDEyOTMgNjQ1LDEzMjMgNTY4LDEzMjMgNDg4LDEzMjMgNDE4LDEyOTAgMzU5LDEyMjUgMzAwLDExNjAgMjcwLDEwNzUgMjcwLDk3MSAyNzAsODc4IDI5OCw4MDIgMzU1LDc0NCA0MTEsNjg1IDQ4MCw2NTYgNTYzLDY1NiA2NDYsNjU2IDcxNSw2ODUgNzY5LDc0NCA4MjIsODAyIDg0OSw4ODMgODQ5LDk4NiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSI4IiBob3Jpei1hZHYteD0iOTk0IiBkPSJNIDM2Miw3OTUgQyAyODcsODIyIDIzMiw4NjEgMTk2LDkxMiAxNjAsOTYzIDE0MiwxMDIzIDE0MiwxMDk0IDE0MiwxMjAxIDE4MCwxMjkwIDI1NywxMzYzIDMzNCwxNDM2IDQzNiwxNDcyIDU2MywxNDcyIDY5MSwxNDcyIDc5NCwxNDM1IDg3MiwxMzYxIDk1MCwxMjg2IDk4OSwxMTk2IDk4OSwxMDg5IDk4OSwxMDIxIDk3MSw5NjIgOTM2LDkxMiA5MDAsODYxIDg0Niw4MjIgNzczLDc5NSA4NjMsNzY2IDkzMiw3MTggOTc5LDY1MyAxMDI2LDU4OCAxMDQ5LDUxMCAxMDQ5LDQxOSAxMDQ5LDI5NCAxMDA1LDE4OCA5MTYsMTAzIDgyNywxOCA3MTEsLTI1IDU2NiwtMjUgNDIxLC0yNSAzMDUsMTggMjE2LDEwNCAxMjcsMTg5IDgzLDI5NiA4Myw0MjQgODMsNTE5IDEwNyw1OTkgMTU2LDY2NCAyMDQsNzI4IDI3Myw3NzIgMzYyLDc5NSBaIE0gMzI2LDExMDAgQyAzMjYsMTAzMSAzNDgsOTc0IDM5Myw5MzAgNDM4LDg4NiA0OTYsODY0IDU2Nyw4NjQgNjM2LDg2NCA2OTMsODg2IDczOCw5MzAgNzgyLDk3MyA4MDQsMTAyNyA4MDQsMTA5MCA4MDQsMTE1NiA3ODEsMTIxMiA3MzYsMTI1NyA2OTAsMTMwMiA2MzMsMTMyNCA1NjUsMTMyNCA0OTYsMTMyNCA0MzksMTMwMiAzOTQsMTI1OCAzNDksMTIxNCAzMjYsMTE2MSAzMjYsMTEwMCBaIE0gMjY4LDQyMyBDIDI2OCwzNzIgMjgwLDMyMiAzMDUsMjc0IDMyOSwyMjYgMzY1LDE4OSA0MTMsMTYzIDQ2MSwxMzYgNTEzLDEyMyA1NjgsMTIzIDY1NCwxMjMgNzI1LDE1MSA3ODEsMjA2IDgzNywyNjEgODY1LDMzMiA4NjUsNDE3IDg2NSw1MDQgODM2LDU3NSA3NzksNjMyIDcyMSw2ODkgNjQ5LDcxNyA1NjIsNzE3IDQ3Nyw3MTcgNDA3LDY4OSAzNTIsNjMzIDI5Niw1NzcgMjY4LDUwNyAyNjgsNDIzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjciIGhvcml6LWFkdi14PSI5NzEiIGQ9Ik0gOTcsMTI3NCBMIDk3LDE0NDcgMTA0NiwxNDQ3IDEwNDYsMTMwNyBDIDk1MywxMjA4IDg2MCwxMDc2IDc2OSw5MTEgNjc3LDc0NiA2MDYsNTc3IDU1Niw0MDMgNTIwLDI4MCA0OTcsMTQ2IDQ4NywwIEwgMzAyLDAgQyAzMDQsMTE1IDMyNywyNTUgMzcwLDQxOCA0MTMsNTgxIDQ3Niw3MzkgNTU3LDg5MSA2MzgsMTA0MiA3MjQsMTE3MCA4MTUsMTI3NCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSI1IiBob3Jpei1hZHYteD0iOTk0IiBkPSJNIDg1LDM4NCBMIDI3NCw0MDAgQyAyODgsMzA4IDMyMSwyMzkgMzcyLDE5MyA0MjMsMTQ2IDQ4NCwxMjMgNTU2LDEyMyA2NDMsMTIzIDcxNiwxNTYgNzc2LDIyMSA4MzYsMjg2IDg2NiwzNzMgODY2LDQ4MSA4NjYsNTg0IDgzNyw2NjUgNzgwLDcyNCA3MjIsNzgzIDY0Niw4MTMgNTUzLDgxMyA0OTUsODEzIDQ0Myw4MDAgMzk2LDc3NCAzNDksNzQ3IDMxMyw3MTMgMjg2LDY3MSBMIDExNyw2OTMgMjU5LDE0NDYgOTg4LDE0NDYgOTg4LDEyNzQgNDAzLDEyNzQgMzI0LDg4MCBDIDQxMiw5NDEgNTA0LDk3MiA2MDEsOTcyIDcyOSw5NzIgODM3LDkyOCA5MjUsODM5IDEwMTMsNzUwIDEwNTcsNjM2IDEwNTcsNDk3IDEwNTcsMzY0IDEwMTgsMjUwIDk0MSwxNTMgODQ3LDM0IDcxOSwtMjUgNTU2LC0yNSA0MjMsLTI1IDMxNCwxMiAyMzAsODcgMTQ1LDE2MiA5NywyNjEgODUsMzg0IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjQiIGhvcml6LWFkdi14PSIxMDQwIiBkPSJNIDY2MiwwIEwgNjYyLDM1MSAyNiwzNTEgMjYsNTE2IDY5NSwxNDY2IDg0MiwxNDY2IDg0Miw1MTYgMTA0MCw1MTYgMTA0MCwzNTEgODQyLDM1MSA4NDIsMCBaIE0gNjYyLDUxNiBMIDY2MiwxMTc3IDIwMyw1MTYgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iMyIgaG9yaXotYWR2LXg9Ijk5NCIgZD0iTSA4NiwzODcgTCAyNjYsNDExIEMgMjg3LDMwOSAzMjIsMjM2IDM3MiwxOTEgNDIxLDE0NiA0ODIsMTIzIDU1MywxMjMgNjM4LDEyMyA3MDksMTUyIDc2OCwyMTEgODI2LDI3MCA4NTUsMzQyIDg1NSw0MjkgODU1LDUxMiA4MjgsNTgwIDc3NCw2MzQgNzIwLDY4NyA2NTEsNzE0IDU2OCw3MTQgNTM0LDcxNCA0OTIsNzA3IDQ0MSw2OTQgTCA0NjEsODUyIEMgNDczLDg1MSA0ODMsODUwIDQ5MCw4NTAgNTY3LDg1MCA2MzYsODcwIDY5Nyw5MTAgNzU4LDk1MCA3ODksMTAxMiA3ODksMTA5NSA3ODksMTE2MSA3NjcsMTIxNiA3MjIsMTI1OSA2NzcsMTMwMiA2MjAsMTMyNCA1NDksMTMyNCA0NzksMTMyNCA0MjEsMTMwMiAzNzQsMTI1OCAzMjcsMTIxNCAyOTcsMTE0OCAyODQsMTA2MCBMIDEwNCwxMDkyIEMgMTI2LDEyMTMgMTc2LDEzMDYgMjU0LDEzNzMgMzMyLDE0MzkgNDI5LDE0NzIgNTQ1LDE0NzIgNjI1LDE0NzIgNjk5LDE0NTUgNzY2LDE0MjEgODMzLDEzODYgODg1LDEzMzkgOTIxLDEyODAgOTU2LDEyMjEgOTc0LDExNTggOTc0LDEwOTEgOTc0LDEwMjggOTU3LDk3MCA5MjMsOTE4IDg4OSw4NjYgODM5LDgyNSA3NzIsNzk0IDg1OSw3NzQgOTI2LDczMyA5NzQsNjcwIDEwMjIsNjA3IDEwNDYsNTI4IDEwNDYsNDMzIDEwNDYsMzA1IDk5OSwxOTcgOTA2LDEwOCA4MTMsMTkgNjk1LC0yNiA1NTIsLTI2IDQyMywtMjYgMzE3LDEyIDIzMiw4OSAxNDcsMTY2IDk4LDI2NSA4NiwzODcgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iMSIgaG9yaXotYWR2LXg9IjU3OCIgZD0iTSA3NjMsMCBMIDU4MywwIDU4MywxMTQ3IEMgNTQwLDExMDYgNDgzLDEwNjQgNDEzLDEwMjMgMzQyLDk4MiAyNzksOTUxIDIyMyw5MzAgTCAyMjMsMTEwNCBDIDMyNCwxMTUxIDQxMiwxMjA5IDQ4NywxMjc2IDU2MiwxMzQzIDYxNiwxNDA5IDY0NywxNDcyIEwgNzYzLDE0NzIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iMCIgaG9yaXotYWR2LXg9Ijk5NCIgZD0iTSA4NSw3MjMgQyA4NSw4OTYgMTAzLDEwMzYgMTM5LDExNDIgMTc0LDEyNDcgMjI3LDEzMjkgMjk4LDEzODYgMzY4LDE0NDMgNDU2LDE0NzIgNTYzLDE0NzIgNjQyLDE0NzIgNzExLDE0NTYgNzcwLDE0MjUgODI5LDEzOTMgODc4LDEzNDcgOTE3LDEyODggOTU2LDEyMjggOTg2LDExNTUgMTAwOCwxMDcwIDEwMzAsOTg0IDEwNDEsODY4IDEwNDEsNzIzIDEwNDEsNTUxIDEwMjMsNDEyIDk4OCwzMDcgOTUzLDIwMSA5MDAsMTE5IDgzMCw2MiA3NTksNCA2NzAsLTI1IDU2MywtMjUgNDIyLC0yNSAzMTEsMjYgMjMwLDEyNyAxMzMsMjQ5IDg1LDQ0OCA4NSw3MjMgWiBNIDI3MCw3MjMgQyAyNzAsNDgyIDI5OCwzMjIgMzU1LDI0MyA0MTEsMTYzIDQ4MCwxMjMgNTYzLDEyMyA2NDYsMTIzIDcxNSwxNjMgNzcyLDI0MyA4MjgsMzIzIDg1Niw0ODMgODU2LDcyMyA4NTYsOTY0IDgyOCwxMTI1IDc3MiwxMjA0IDcxNSwxMjgzIDY0NSwxMzIzIDU2MSwxMzIzIDQ3OCwxMzIzIDQxMiwxMjg4IDM2MywxMjE4IDMwMSwxMTI5IDI3MCw5NjQgMjcwLDcyMyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSItIiBob3Jpei1hZHYteD0iNTc4IiBkPSJNIDY1LDQ0MCBMIDY1LDYyMSA2MTgsNjIxIDYxOCw0NDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iICIgaG9yaXotYWR2LXg9IjU3NyIvPgogIDwvZm9udD4KIDwvZGVmcz4KIDxkZWZzIGNsYXNzPSJUZXh0U2hhcGVJbmRleCI+CiAgPGcgb29vOnNsaWRlPSJpZDEiIG9vbzppZC1saXN0PSJpZDMgaWQ0IGlkNSBpZDYgaWQ3IGlkOCBpZDkgaWQxMCBpZDExIGlkMTIgaWQxMyBpZDE0IGlkMTUgaWQxNiBpZDE3IGlkMTgiLz4KIDwvZGVmcz4KIDxkZWZzIGNsYXNzPSJFbWJlZGRlZEJ1bGxldENoYXJzIj4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtNTczNTYiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDU4MCwxMTQxIEwgMTE2Myw1NzEgNTgwLDAgLTQsNTcxIDU4MCwxMTQxIFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTU3MzU0IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSA4LDExMjggTCAxMTM3LDExMjggMTEzNywwIDgsMCA4LDExMjggWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtMTAxNDYiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDE3NCwwIEwgNjAyLDczOSAxNzQsMTQ4MSAxNDU2LDczOSAxNzQsMCBaIE0gMTM1OCw3MzkgTCAzMDksMTM0NiA2NTksNzM5IDEzNTgsNzM5IFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTEwMTMyIiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAyMDE1LDczOSBMIDEyNzYsMCA3MTcsMCAxMjYwLDU0MyAxNzQsNTQzIDE3NCw5MzYgMTI2MCw5MzYgNzE3LDE0ODEgMTI3NCwxNDgxIDIwMTUsNzM5IFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTEwMDA3IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAwLC0yIEMgLTcsMTQgLTE2LDI3IC0yNSwzNyBMIDM1Niw1NjcgQyAyNjIsODIzIDIxNSw5NTIgMjE1LDk1NCAyMTUsOTc5IDIyOCw5OTIgMjU1LDk5MiAyNjQsOTkyIDI3Niw5OTAgMjg5LDk4NyAzMTAsOTkxIDMzMSw5OTkgMzU0LDEwMTIgTCAzODEsOTk5IDQ5Miw3NDggNzcyLDEwNDkgODM2LDEwMjQgODYwLDEwNDkgQyA4ODEsMTAzOSA5MDEsMTAyNSA5MjIsMTAwNiA4ODYsOTM3IDgzNSw4NjMgNzcwLDc4NCA3NjksNzgzIDcxMCw3MTYgNTk0LDU4NCBMIDc3NCwyMjMgQyA3NzQsMTk2IDc1MywxNjggNzExLDEzOSBMIDcyNywxMTkgQyA3MTcsOTAgNjk5LDc2IDY3Miw3NiA2NDEsNzYgNTcwLDE3OCA0NTcsMzgxIEwgMTY0LC03NiBDIDE0MiwtMTEwIDExMSwtMTI3IDcyLC0xMjcgMzAsLTEyNyA5LC0xMTAgOCwtNzYgMSwtNjcgLTIsLTUyIC0yLC0zMiAtMiwtMjMgLTEsLTEzIDAsLTIgWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtMTAwMDQiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDI4NSwtMzMgQyAxODIsLTMzIDExMSwzMCA3NCwxNTYgNTIsMjI4IDQxLDMzMyA0MSw0NzEgNDEsNTQ5IDU1LDYxNiA4Miw2NzIgMTE2LDc0MyAxNjksNzc4IDI0MCw3NzggMjkzLDc3OCAzMjgsNzQ3IDM0Niw2ODQgTCAzNjksNTA4IEMgMzc3LDQ0NCAzOTcsNDExIDQyOCw0MTAgTCAxMTYzLDExMTYgQyAxMTc0LDExMjcgMTE5NiwxMTMzIDEyMjksMTEzMyAxMjcxLDExMzMgMTI5MiwxMTE4IDEyOTIsMTA4NyBMIDEyOTIsOTY1IEMgMTI5Miw5MjkgMTI4Miw5MDEgMTI2Miw4ODEgTCA0NDIsNDcgQyAzOTAsLTYgMzM4LC0zMyAyODUsLTMzIFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTk2NzkiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDgxMywwIEMgNjMyLDAgNDg5LDU0IDM4MywxNjEgMjc2LDI2OCAyMjMsNDExIDIyMyw1OTIgMjIzLDc3MyAyNzYsOTE2IDM4MywxMDIzIDQ4OSwxMTMwIDYzMiwxMTg0IDgxMywxMTg0IDk5MiwxMTg0IDExMzYsMTEzMCAxMjQ1LDEwMjMgMTM1Myw5MTYgMTQwNyw3NzIgMTQwNyw1OTIgMTQwNyw0MTIgMTM1MywyNjggMTI0NSwxNjEgMTEzNiw1NCA5OTIsMCA4MTMsMCBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS04MjI2IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAzNDYsNDU3IEMgMjczLDQ1NyAyMDksNDgzIDE1NSw1MzUgMTAxLDU4NiA3NCw2NDkgNzQsNzIzIDc0LDc5NiAxMDEsODU5IDE1NSw5MTEgMjA5LDk2MyAyNzMsOTg5IDM0Niw5ODkgNDE5LDk4OSA0ODAsOTYzIDUzMSw5MTAgNTgyLDg1OSA2MDgsNzk2IDYwOCw3MjMgNjA4LDY0OCA1ODMsNTg2IDUzMiw1MzUgNDgyLDQ4MyA0MjAsNDU3IDM0Niw0NTcgWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtODIxMSIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gLTQsNDU5IEwgMTEzNSw0NTkgMTEzNSw2MDYgLTQsNjA2IC00LDQ1OSBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS02MTU0OCIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gMTczLDc0MCBDIDE3Myw5MDMgMjMxLDEwNDMgMzQ2LDExNTkgNDYyLDEyNzQgNjAxLDEzMzIgNzY1LDEzMzIgOTI4LDEzMzIgMTA2NywxMjc0IDExODMsMTE1OSAxMjk5LDEwNDMgMTM1Nyw5MDMgMTM1Nyw3NDAgMTM1Nyw1NzcgMTI5OSw0MzcgMTE4MywzMjIgMTA2NywyMDYgOTI4LDE0OCA3NjUsMTQ4IDYwMSwxNDggNDYyLDIwNiAzNDYsMzIyIDIzMSw0MzcgMTczLDU3NyAxNzMsNzQwIFoiLz4KICA8L2c+CiA8L2RlZnM+CiA8ZGVmcyBjbGFzcz0iVGV4dEVtYmVkZGVkQml0bWFwcyIvPgogPGcgY2xhc3M9IlNsaWRlR3JvdXAiPgogIDxnPgogICA8ZyBpZD0iY29udGFpbmVyLWlkMSI+CiAgICA8ZyBpZD0iaWQxIiBjbGFzcz0iU2xpZGUiIGNsaXAtcGF0aD0idXJsKCNwcmVzZW50YXRpb25fY2xpcF9wYXRoKSI+CiAgICAgPGcgY2xhc3M9IlBhZ2UiPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjM2OTkiIHk9IjcyOTkiIHdpZHRoPSI2MjAzIiBoZWlnaHQ9IjgwMyIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigyMjksMjQyLDI1MCkiIHN0cm9rZT0ibm9uZSIgZD0iTSA2ODAwLDgxMDAgTCAzNzAwLDgxMDAgMzcwMCw3MzAwIDk5MDAsNzMwMCA5OTAwLDgxMDAgNjgwMCw4MTAwIFoiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gNjgwMCw4MTAwIEwgMzcwMCw4MTAwIDM3MDAsNzMwMCA5OTAwLDczMDAgOTkwMCw4MTAwIDY4MDAsODEwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0NzA1IiB5PSI3ODEwIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5vcmcub3Blbmdpcy5hbm5vdGF0aW9uPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQ0Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMzY5OSIgeT0iNjI5OSIgd2lkdGg9IjYyMDMiIGhlaWdodD0iODAzIi8+CiAgICAgICAgPHBhdGggZmlsbD0icmdiKDIyOSwyNDIsMjUwKSIgc3Ryb2tlPSJub25lIiBkPSJNIDY4MDAsNzEwMCBMIDM3MDAsNzEwMCAzNzAwLDYzMDAgOTkwMCw2MzAwIDk5MDAsNzEwMCA2ODAwLDcxMDAgWiIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSA2ODAwLDcxMDAgTCAzNzAwLDcxMDAgMzcwMCw2MzAwIDk5MDAsNjMwMCA5OTAwLDcxMDAgNjgwMCw3MTAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjUyNzYiIHk9IjY4MTAiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPm9yZy5vcGVuZ2lzLnV0aWw8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDUiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIzNjk5IiB5PSI1Mjk5IiB3aWR0aD0iNjIwMyIgaGVpZ2h0PSI4MDMiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjI5LDI0MiwyNTApIiBzdHJva2U9Im5vbmUiIGQ9Ik0gNjgwMCw2MTAwIEwgMzcwMCw2MTAwIDM3MDAsNTMwMCA5OTAwLDUzMDAgOTkwMCw2MTAwIDY4MDAsNjEwMCBaIi8+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJyZ2IoMCwwLDApIiBkPSJNIDY4MDAsNjEwMCBMIDM3MDAsNjEwMCAzNzAwLDUzMDAgOTkwMCw1MzAwIDk5MDAsNjEwMCA2ODAwLDYxMDAgWiIvPgogICAgICAgIDx0ZXh0IGNsYXNzPSJUZXh0U2hhcGUiPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iNDg5NSIgeT0iNTgxMCI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+b3JnLm9wZW5naXMubWV0YWRhdGE8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDYiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIzNjk5IiB5PSIzODk5IiB3aWR0aD0iNDgwMyIgaGVpZ2h0PSIxMjAzIi8+CiAgICAgICAgPHBhdGggZmlsbD0icmdiKDIyOSwyNDIsMjUwKSIgc3Ryb2tlPSJub25lIiBkPSJNIDYxMDAsNTEwMCBMIDM3MDAsNTEwMCAzNzAwLDM5MDAgODUwMCwzOTAwIDg1MDAsNTEwMCA2MTAwLDUxMDAgWiIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSA2MTAwLDUxMDAgTCAzNzAwLDUxMDAgMzcwMCwzOTAwIDg1MDAsMzkwMCA4NTAwLDUxMDAgNjEwMCw1MTAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjM5NTAiIHk9IjQ0MzIiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPm9yZy5vcGVuZ2lzLnJlZmVyZW5jaW5nPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIzOTUwIiB5PSI0Nzg4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5vcmcub3Blbmdpcy5wYXJhbWV0ZXI8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLlBvbHlQb2x5Z29uU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkNyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjM2OTkiIHk9IjI4OTkiIHdpZHRoPSI2MjAzIiBoZWlnaHQ9IjIyMDMiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjI5LDI0MiwyNTApIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMzcwMCwyOTAwIEwgOTkwMCwyOTAwIDk5MDAsNTEwMCA4NjM5LDUxMDAgODYzOSwzNzAwIDM3MDAsMzcwMCAzNzAwLDI5MDAgWiIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSAzNzAwLDI5MDAgTCA5OTAwLDI5MDAgOTkwMCw1MTAwIDg2MzksNTEwMCA4NjM5LDM3MDAgMzcwMCwzNzAwIDM3MDAsMjkwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0ODk1IiB5PSIzMzg4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5vcmcub3Blbmdpcy5nZW9tZXRyeTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkOCI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9Ijk5OSIgeT0iMjkwMCIgd2lkdGg9IjI2MDAiIGhlaWdodD0iODAxIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzUzcHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxMjQ5IiB5PSIzNDIxIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5JU08gMTkxMDc8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDkiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI5OTkiIHk9IjUzMDAiIHdpZHRoPSIyNjAwIiBoZWlnaHQ9IjgwMSIvPgogICAgICAgIDx0ZXh0IGNsYXNzPSJUZXh0U2hhcGUiPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjM1M3B4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTI0OSIgeT0iNTgyMSI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+SVNPIDE5MTE1PC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxMCI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9Ijk5OSIgeT0iNjMwMCIgd2lkdGg9IjI2MDAiIGhlaWdodD0iODAxIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzUzcHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxMjQ5IiB5PSI2ODIxIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5JU08gMTkxMDM8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDExIj4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iOTk5IiB5PSIzOTAwIiB3aWR0aD0iMjYwMCIgaGVpZ2h0PSIxMjAxIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzUzcHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxMjQ5IiB5PSI0NDI0Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5JU08gMTkxMTE8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzNTNweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjEyNDkiIHk9IjQ4MTgiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPk9HQyAwMS0wMDk8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDEyIj4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMzcwMCIgeT0iMTAwMCIgd2lkdGg9IjYyMDEiIGhlaWdodD0iODAxIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iNDIzcHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI1MzgyIiB5PSIxNTQ4Ij48dHNwYW4gZmlsbD0icmdiKDAsMTAyLDE1MykiIHN0cm9rZT0ibm9uZSI+SmF2YSBwYWNrYWdlczwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTMiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxMDg5OSIgeT0iNTMwMCIgd2lkdGg9IjYyMDMiIGhlaWdodD0iODAzIi8+CiAgICAgICAgPHBhdGggZmlsbD0icmdiKDIyOSwyNDIsMjUwKSIgc3Ryb2tlPSJub25lIiBkPSJNIDE0MDAwLDYxMDEgTCAxMDkwMCw2MTAxIDEwOTAwLDUzMDEgMTcxMDAsNTMwMSAxNzEwMCw2MTAxIDE0MDAwLDYxMDEgWiIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSAxNDAwMCw2MTAxIEwgMTA5MDAsNjEwMSAxMDkwMCw1MzAxIDE3MTAwLDUzMDEgMTcxMDAsNjEwMSAxNDAwMCw2MTAxIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjEyNDc2IiB5PSI1ODExIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5vcGVuZ2lzLm1ldGFkYXRhPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxNCI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjEwODk5IiB5PSIzOTAwIiB3aWR0aD0iNDgwMyIgaGVpZ2h0PSIxMjAzIi8+CiAgICAgICAgPHBhdGggZmlsbD0icmdiKDIyOSwyNDIsMjUwKSIgc3Ryb2tlPSJub25lIiBkPSJNIDEzMzAwLDUxMDEgTCAxMDkwMCw1MTAxIDEwOTAwLDM5MDEgMTU3MDAsMzkwMSAxNTcwMCw1MTAxIDEzMzAwLDUxMDEgWiIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSAxMzMwMCw1MTAxIEwgMTA5MDAsNTEwMSAxMDkwMCwzOTAxIDE1NzAwLDM5MDEgMTU3MDAsNTEwMSAxMzMwMCw1MTAxIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjExNDkwIiB5PSI0NjExIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5vcGVuZ2lzLnJlZmVyZW5jaW5nPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5Qb2x5UG9seWdvblNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDE1Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMTA4OTkiIHk9IjI5MDAiIHdpZHRoPSI2MjAzIiBoZWlnaHQ9IjIyMDMiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjI5LDI0MiwyNTApIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMTA5MDAsMjkwMSBMIDE3MTAwLDI5MDEgMTcxMDAsNTEwMSAxNTgzOSw1MTAxIDE1ODM5LDM3MDEgMTA5MDAsMzcwMSAxMDkwMCwyOTAxIFoiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gMTA5MDAsMjkwMSBMIDE3MTAwLDI5MDEgMTcxMDAsNTEwMSAxNTgzOSw1MTAxIDE1ODM5LDM3MDEgMTA5MDAsMzcwMSAxMDkwMCwyOTAxIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjEyNDc2IiB5PSIzMzg5Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5vcGVuZ2lzLmdlb21ldHJ5PC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxNiI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjEwOTAwIiB5PSIxMDAxIiB3aWR0aD0iNjIwMSIgaGVpZ2h0PSI4MDEiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSI0MjNweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjEyMzY4IiB5PSIxNTQ5Ij48dHNwYW4gZmlsbD0icmdiKDAsMTAyLDE1MykiIHN0cm9rZT0ibm9uZSI+UHl0aG9uIHBhY2thZ2VzPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxNyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjM3MDAiIHk9IjE4OTkiIHdpZHRoPSI2MjAzIiBoZWlnaHQ9IjgwMyIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigyMjksMjQyLDI1MCkiIHN0cm9rZT0ibm9uZSIgZD0iTSA2ODAxLDI3MDAgTCAzNzAxLDI3MDAgMzcwMSwxOTAwIDk5MDEsMTkwMCA5OTAxLDI3MDAgNjgwMSwyNzAwIFoiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gNjgwMSwyNzAwIEwgMzcwMSwyNzAwIDM3MDEsMTkwMCA5OTAxLDE5MDAgOTkwMSwyNzAwIDY4MDEsMjcwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0OTkxIiB5PSIyNDEwIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5vcmcub3Blbmdpcy5mZWF0dXJlPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxOCI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjEwMDAiIHk9IjE4MDAiIHdpZHRoPSIyNjAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzNTNweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjEyNTAiIHk9IjIyMjQiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPklTTyAxOTEwOTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjM1M3B4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTI1MCIgeT0iMjYxOCI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+T0dDIDE0LTA4MzwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgIDwvZz4KICAgIDwvZz4KICAgPC9nPgogIDwvZz4KIDwvZz4KPC9zdmc+" alt="packages">
</div>
<div class="title">Figure 1. ISO specifications and GeoAPI packages mapping</div>
</div>
<div class="paragraph">
<p>The <code>opengis.annotation</code> package provides the annotation system
used to document the origin and obligation level of all methods and types in GeoAPI.
These annotations are available through introspection at runtime for any code which wishes to exploit this information.
The base of GeoAPI is formed by a formal mapping of the core types used by ISO and OGC standards to Java and Python equivalents,
along with extra types in the <code>opengis.util</code> package for types not provided in the standard libraries.
The packages in the <code>opengis.metadata</code> namespace cover the types defined in the ISO 19115 <em>Metadata</em> specification,
which are data structures describing other structures.
The packages in the <code>opengis.parameter</code> and <code>opengis.referencing</code> namespaces
implement the types from the ISO 19111 <em>Spatial Referencing by Coordinates</em> specification
complemented by the mathematical operator types from the OGC 01-009 <em>Coordinate Transformation Services</em> implementation specification.
The packages in the <code>opengis.geometry</code> namespace cover the types defined in the ISO 19107 <em>Spatial Schema</em> specification,
although in version 4.0 of the library only defines the elements from that specification needed by the geo-referencing types.
Finally the packages in the <code>opengis.feature</code> namespace
covers the meta-classes defined in the ISO 19109 <em>Rules for application schema</em> specification,
completed by the dynamic attributes defined in OGC 14-083 <em>Moving Features</em> specification.
This package is not needed for dynamic languages like Python.</p>
</div>
<div class="sect2">
<h3 id="general-mapping"><a class="anchor" href="#general-mapping"></a>6.1. General mapping rules</h3>
<div class="paragraph">
<p>This section gives high-level guidance in the mapping from UML to Java and Python API applying to all GeoAPI types.
Other sections after this one will focus on specific type subsets (metadata, referencing, <em>etc</em>).
Those guidance are not strict rules; departures exist on a case-by-case basis when the semantic justify them.</p>
</div>
<div class="sect3">
<h4 id="naming"><a class="anchor" href="#naming"></a>6.1.1. Naming conventions</h4>
<div class="paragraph">
<p>The interface and property names defined in OGC/ISO standards may be modified
for compliance with the conventions in use in target programming languages.
The main changes are described below:</p>
</div>
<div class="sect4">
<h5>Interfaces</h5>
<div class="paragraph">
<p>The two-letter prefixes are dropped.
For example <code>MD_Metadata</code> and <code>CI_Citation</code> interfaces are named <code>Metadata</code> and <code>Citation</code> in Java and Python.
The camel cases convention (for example <code>CoordinateSystemAxis</code>) is kept unchanged for interfaces.</p>
</div>
</div>
<div class="sect4">
<h5>Code lists and enumerations</h5>
<div class="paragraph">
<p>The two-letter prefixes are dropped in the same way than for interfaces.
Then if the type name ends with the <code>Code</code> suffix, that suffix is dropped too in strongly-typed languages like Java.
For example the ISO 19115 <code>TopicCategoryCode</code> code list is named <code>TopicCategory</code> in Java classes.
This renaming is not applied to more dynamic languages like Python,
because the naming convention can be a compensation for the absence of compile-time type checks.</p>
</div>
</div>
<div class="sect4">
<h5>Properties</h5>
<div class="paragraph">
<p>The name changes depend on the target programming language and on the multiplicity.
In Java, accessor methods start with the <code>get</code> prefix and are followed by their property name in camel cases.
For example the ISO 19111 <code>coordinateSystem</code> property become a method named <code>getCoordinateSystem()</code> in Java.
But in Python, no prefix is added and the camel cases convention is replaced by the snake cases convention.
For example ISO 19111 <code>coordinateSystem</code> property become a property named <code>coordinate_system</code> in Python.
In all languages, if a property allows more than one value, then the plural form of its noun may be used.
The plural form hints the developers that they may need to use indexes or iterators for accessing elements.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 3. Example of entity name changes</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO entity</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Name in Java</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Name in Python</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>CI_Citation</code> interface</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Citation</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Citation</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>CoordinateSystemAxis</code> interface</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>CoordinateSystemAxis</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>CoordinateSystemAxis</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>TopicCategoryCode</code> code list</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>TopicCategory</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>TopicCategoryCode</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>coordinateSystem</code> property</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>getCoordinateSystem()</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>coordinate_system</code></p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect3">
<h4 id="multiplicity"><a class="anchor" href="#multiplicity"></a>6.1.2. Multiplicity conventions</h4>
<div class="paragraph">
<p>The UML diagrams may specify arbitrary multiplicities (minimum and maximum number of occurrences) for each property.
But GeoAPI recognizes only the following four multiplicities,
materialized in the API <a href="#annotations">as annotations</a> and in the method signatures.
If a different multiplicity is needed, then [0 … ∞] should be used
with a restriction documented in the text attached to the property.</p>
</div>
<div class="ulist">
<ul>
<li>
<p><strong>[0 … 0]</strong> — the property can not be set (this happen sometime in subtypes).</p>
</li>
<li>
<p><strong>[0 … 1]</strong> — the property is optional or conditional.</p>
</li>
<li>
<p><strong>[1 … 1]</strong> — the property is mandatory.</p>
</li>
<li>
<p><strong>[0 … ∞]</strong> — the property can appear an arbitrary number of times, including zero or one.</p>
</li>
</ul>
</div>
<div class="paragraph">
<p>Some programming languages have an <code>Optional</code> construct for differentiating the [0 … 1] and [1 … 1] cases.
This construct is used where appropriate, but shall be considered only as a hint.
It may appear in a mandatory property if that property was optional in the parent interface.
Conversely, absence of <code>Optional</code> construct is not a guarantee that the value will never be null.
Some properties fall in gray area, where they are <em>usually</em> not null but may be null in some rare situations.
For example the <code>ellipsoid</code> property of a Geodetic Reference Frame is mandatory when used in the context of
geographic or projected coordinate reference systems, which are by far the most common cases.
Even when used in other contexts, the ellipsoid is optional but still recommended.
Consequently GeoAPI does not use the <code>Optional</code> construct for the <code>ellipsoid</code> property
in order to keep the most common usages simpler, but robust applications should be prepared to handle a null value.
Developers should refer to the API documentation for the policy on null values.</p>
</div>
</div>
<div class="sect3">
<h4 id="annotations"><a class="anchor" href="#annotations"></a>6.1.3. Annotated API</h4>
<div class="paragraph">
<p>The <code>opengis.annotation</code> package allows GeoAPI to document the UML elements
from the various specification documents used for defining the Java and Python constructs.
Those annotations encode the source document, stereotype, original name, and obligation level
of the various types, properties and operations published by GeoAPI.
The source document may be completed by a version number when the GeoAPI construct
is based on a different edition of a normative document than the dated references
listed in the <a href="#references">references clause</a>.
GeoAPI defines two annotations in the Java language (no annotation in Python):
<code>@UML</code> which is applied on types and properties (fields or methods), and
<code>@Classifier</code> which can be applied only on types.
Those annotations are shown in the figure below:</p>
</div>
<div class="imageblock">
<div class="content">
<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj4KPHN2ZyB2ZXJzaW9uPSIxLjIiIHdpZHRoPSIxNTIuM21tIiBoZWlnaHQ9Ijk4LjNtbSIgdmlld0JveD0iMzc4NSAxMDA4NSAxNTIzMCA5ODMwIiBwcmVzZXJ2ZUFzcGVjdFJhdGlvPSJ4TWlkWU1pZCIgZmlsbC1ydWxlPSJldmVub2RkIiBzdHJva2Utd2lkdGg9IjI4LjIyMiIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczpvb289Imh0dHA6Ly94bWwub3Blbm9mZmljZS5vcmcvc3ZnL2V4cG9ydCIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhtbG5zOnByZXNlbnRhdGlvbj0iaHR0cDovL3N1bi5jb20veG1sbnMvc3Rhcm9mZmljZS9wcmVzZW50YXRpb24iIHhtbG5zOnNtaWw9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvU01JTDIwLyIgeG1sbnM6YW5pbT0idXJuOm9hc2lzOm5hbWVzOnRjOm9wZW5kb2N1bWVudDp4bWxuczphbmltYXRpb246MS4wIiB4bWw6c3BhY2U9InByZXNlcnZlIj4KIDxkZWZzIGNsYXNzPSJDbGlwUGF0aEdyb3VwIj4KICA8Y2xpcFBhdGggaWQ9InByZXNlbnRhdGlvbl9jbGlwX3BhdGgiIGNsaXBQYXRoVW5pdHM9InVzZXJTcGFjZU9uVXNlIj4KICAgPHJlY3QgeD0iMzc4NSIgeT0iMTAwODUiIHdpZHRoPSIxNTIzMCIgaGVpZ2h0PSI5ODMwIi8+CiAgPC9jbGlwUGF0aD4KICA8Y2xpcFBhdGggaWQ9InByZXNlbnRhdGlvbl9jbGlwX3BhdGhfc2hyaW5rIiBjbGlwUGF0aFVuaXRzPSJ1c2VyU3BhY2VPblVzZSI+CiAgIDxyZWN0IHg9IjM4MDAiIHk9IjEwMDk0IiB3aWR0aD0iMTUyMDAiIGhlaWdodD0iOTgxMSIvPgogIDwvY2xpcFBhdGg+CiA8L2RlZnM+CiA8ZGVmcz4KICA8Zm9udCBpZD0iRW1iZWRkZWRGb250XzEiIGhvcml6LWFkdi14PSIyMDQ4Ij4KICAgPGZvbnQtZmFjZSBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8gZW1iZWRkZWQiIHVuaXRzLXBlci1lbT0iMjA0OCIgZm9udC13ZWlnaHQ9Im5vcm1hbCIgZm9udC1zdHlsZT0ibm9ybWFsIiBhc2NlbnQ9IjE4NzAiIGRlc2NlbnQ9IjQzOSIvPgogICA8bWlzc2luZy1nbHlwaCBob3Jpei1hZHYteD0iMjA0OCIgZD0iTSAwLDAgTCAyMDQ3LDAgMjA0NywyMDQ3IDAsMjA0NyAwLDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iwqAiIGhvcml6LWFkdi14PSIxIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJ5IiBob3Jpei1hZHYteD0iOTcxIiBkPSJNIDEwNjUsMTA0MiBMIDY3MCwwIEMgNjEzLC0xNTMgNTU2LC0yNjAgNTAwLC0zMjEgNDQzLC0zODIgMzc5LC00MTIgMzA3LC00MTIgMjQzLC00MTIgMTgwLC00MDAgMTE3LC0zNzcgTCAxNDMsLTI1MiBDIDE5NCwtMjY4IDIzOSwtMjc2IDI3OSwtMjc2IDMzNSwtMjc2IDM4MSwtMjU0IDQxNywtMjExIDQ1MywtMTY4IDQ4OSwtOTcgNTI2LDIgTCAxMzUsMTA0MiAzMDUsMTA0MiA2MDAsMjExIDg5NSwxMDQyIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InYiIGhvcml6LWFkdi14PSI5NzEiIGQ9Ik0gMTA2NSwxMDQyIEwgNjcwLDAgNTI4LDAgMTM1LDEwNDIgMzA1LDEwNDIgNjAwLDIxMyA4OTUsMTA0MiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJ1IiBob3Jpei1hZHYteD0iODMyIiBkPSJNIDEwMTYsMCBMIDg2MCwwIDg2MCwxNTQgQyA3NDgsMzUgNjMwLC0yNSA1MDYsLTI1IDMxMSwtMjUgMjEzLDkzIDIxMywzMzAgTCAyMTMsMTA0MiAzNjksMTA0MiAzNjksMzc1IEMgMzY5LDI4NiAzODMsMjIwIDQxMSwxNzcgNDM5LDEzMyA0ODYsMTExIDU1MywxMTEgNjQ4LDExMSA3NTEsMTcwIDg2MCwyODkgTCA4NjAsMTA0MiAxMDE2LDEwNDIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0idCIgaG9yaXotYWR2LXg9IjgwOSIgZD0iTSAxMDI4LDQ3IEMgOTIzLC0xIDgyMCwtMjUgNzE5LC0yNSA1MzMsLTI1IDQ0MCw3MyA0NDAsMjcwIEwgNDQwLDkwNyAyNDAsOTA3IDI0MCwxMDQyIDQ0MCwxMDQyIDQ0MCwxMzI3IDU5MiwxMzI3IDU5MiwxMDQyIDk0NiwxMDQyIDk0Niw5MDcgNTkyLDkwNyA1OTIsMzExIEMgNTkyLDI0OCA2MDYsMTk5IDYzNCwxNjQgNjYyLDEyOSA3MDAsMTExIDc0OCwxMTEgODMzLDExMSA5MTgsMTMwIDEwMDEsMTY4IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InMiIGhvcml6LWFkdi14PSI3ODYiIGQ9Ik0gOTA5LDg5MyBDIDgyMiw5MjIgNzM5LDkzNiA2NjIsOTM2IDU3Nyw5MzYgNTEwLDkyMiA0NjEsODk0IDQxMiw4NjUgMzg3LDgyNyAzODcsNzgwIDM4Nyw3NTAgMzk4LDcyNCA0MjEsNzAyIDQ0NCw2NzkgNDg5LDY1OCA1NTcsNjM5IEwgNzA2LDU5NiBDIDc3NSw1NzcgODMxLDU1NCA4NzQsNTI3IDkxNyw1MDAgOTUwLDQ2NyA5NzIsNDI5IDk5MywzOTAgMTAwNCwzNDYgMTAwNCwyOTUgMTAwNCwxOTYgOTYyLDExNyA4NzksNjAgNzk2LDIgNjkwLC0yNyA1NjEsLTI3IDQzMywtMjcgMzI3LC04IDI0NCwzMSBMIDI3NCwxNjYgQyAzNjEsMTI1IDQ1NSwxMDQgNTU1LDEwNCA2NDIsMTA0IDcxMiwxMjAgNzY3LDE1MyA4MjEsMTg1IDg0OCwyMjggODQ4LDI4MSA4NDgsMzE3IDgzNSwzNDkgODA4LDM3OCA3ODEsNDA2IDcxNyw0MzUgNjE3LDQ2NiA1MzYsNDkxIDQ3Nyw1MDkgNDM5LDUyMiA0MDEsNTM0IDM2Niw1NTIgMzMzLDU3NiAzMDAsNjAwIDI3Niw2MjkgMjU5LDY2MiAyNDIsNjk1IDIzMyw3MzMgMjMzLDc3NCAyMzMsODY0IDI3Miw5MzUgMzUxLDk4OCA0MzAsMTA0MSA1MzQsMTA2NyA2NjQsMTA2NyA3NDgsMTA2NyA4NDEsMTA1MyA5NDQsMTAyNCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJyIiBob3Jpei1hZHYteD0iNzE3IiBkPSJNIDEwMzQsMTAxNCBMIDk3MSw4NjQgQyA5MDIsODk0IDg0Myw5MDkgNzkzLDkwOSA3NDcsOTA5IDcwMiw4OTYgNjU4LDg3MCA2MTQsODQzIDU3NCw4MDYgNTM3LDc1NyA1MDAsNzA4IDQ4MSw2ODAgNDgxLDY3NCBMIDQ4MSwwIDMyNiwwIDMyNiwxMDQyIDQ4MSwxMDQyIDQ4MSw4MzEgQyA1NjQsOTg4IDY3MiwxMDY3IDgwNSwxMDY3IDg4NCwxMDY3IDk2MCwxMDQ5IDEwMzQsMTAxNCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJwIiBob3Jpei1hZHYteD0iODc4IiBkPSJNIDM3MSw5MDcgQyA0MDYsOTU1IDQ1Myw5OTQgNTEyLDEwMjMgNTcxLDEwNTIgNjMzLDEwNjcgNjk4LDEwNjcgODE4LDEwNjcgOTEyLDEwMTggOTgxLDkxOSAxMDQ5LDgyMCAxMDgzLDY5MSAxMDgzLDUzMiAxMDgzLDM3MiAxMDQ3LDIzOSA5NzYsMTMzIDkwNCwyNiA4MDcsLTI3IDY4NiwtMjcgNjIxLC0yNyA1NjEsLTExIDUwNSwyMiA0NDgsNTQgNDA0LDk3IDM3MSwxNTAgTCAzNzEsLTQxMiAyMTUsLTQxMiAyMTUsMTA0MiAzNzEsMTA0MiBaIE0gMzcxLDc1MiBMIDM3MSwzMTkgQyA0MDAsMjYwIDQ0MCwyMTEgNDkwLDE3MiA1NDAsMTMzIDU5MiwxMTMgNjQ1LDExMyA3MjksMTEzIDc5NiwxNTIgODQ2LDIzMSA4OTUsMzEwIDkyMCw0MDcgOTIwLDUyNCA5MjAsNjQ0IDg5Niw3NDIgODQ5LDgxOCA4MDIsODk0IDczNiw5MzIgNjUxLDkzMiA1NDEsOTMyIDQ0OCw4NzIgMzcxLDc1MiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJvIiBob3Jpei1hZHYteD0iOTQ3IiBkPSJNIDYxNCwxMDY3IEMgNzU3LDEwNjcgODcxLDEwMTcgOTU2LDkxNyAxMDQxLDgxNyAxMDgzLDY4NSAxMDgzLDUyMiAxMDgzLDM1NyAxMDQxLDIyNSA5NTYsMTI0IDg3MSwyMyA3NTcsLTI3IDYxNCwtMjcgNDcwLC0yNyAzNTYsMjMgMjcyLDEyNCAxODcsMjI0IDE0NSwzNTcgMTQ1LDUyMiAxNDUsNjg2IDE4Nyw4MTggMjcyLDkxOCAzNTcsMTAxNyA0NzEsMTA2NyA2MTQsMTA2NyBaIE0gNjE0LDEwOSBDIDcwOSwxMDkgNzgzLDE0OSA4MzgsMjI5IDg5MywzMDkgOTIwLDQwNiA5MjAsNTIwIDkyMCw2NDAgODkzLDczOSA4MzksODE2IDc4NSw4OTMgNzEwLDkzMiA2MTQsOTMyIDUxNyw5MzIgNDQyLDg5NCAzODksODE4IDMzNiw3NDEgMzA5LDY0MiAzMDksNTIwIDMwOSw0MDQgMzM2LDMwNyAzOTAsMjI4IDQ0NCwxNDkgNTE5LDEwOSA2MTQsMTA5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im4iIGhvcml6LWFkdi14PSI4MzIiIGQ9Ik0gMTAxOCwwIEwgODYyLDAgODYyLDY2OCBDIDg2Miw3NjEgODQ3LDgyOCA4MTgsODcwIDc4OCw5MTEgNzQxLDkzMiA2NzgsOTMyIDU4Myw5MzIgNDgwLDg3MyAzNzEsNzU0IEwgMzcxLDAgMjE1LDAgMjE1LDEwNDIgMzcxLDEwNDIgMzcxLDg4OSBDIDQ4MywxMDA4IDYwMSwxMDY3IDcyNSwxMDY3IDkyMCwxMDY3IDEwMTgsOTQ5IDEwMTgsNzEzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im0iIGhvcml6LWFkdi14PSI5OTQiIGQ9Ik0gMTEwMCwwIEwgOTQ0LDAgOTQ0LDY4NCBDIDk0NCw4NDkgOTEzLDkzMiA4NTIsOTMyIDgyMSw5MzIgNzg2LDkwNCA3NDksODQ3IDcxMSw3OTAgNjkyLDc1OCA2OTIsNzUyIEwgNjkyLDAgNTM3LDAgNTM3LDY4NCBDIDUzNyw4NDkgNTA2LDkzMiA0NDQsOTMyIDQxMyw5MzIgMzc5LDkwNCAzNDIsODQ3IDMwNCw3OTAgMjg1LDc1OCAyODUsNzUyIEwgMjg1LDAgMTI5LDAgMTI5LDEwNDIgMjc5LDEwNDIgMjc5LDg4NSBDIDMwOCw5NDAgMzQzLDk4NSAzODQsMTAxOCA0MjQsMTA1MSA0NjQsMTA2NyA1MDQsMTA2NyA1NDYsMTA2NyA1ODQsMTA1MSA2MTcsMTAxOCA2NTAsOTg1IDY3Myw5NDAgNjg2LDg4NSA3MTUsOTQwIDc1MCw5ODUgNzkxLDEwMTggODMyLDEwNTEgODcyLDEwNjcgOTExLDEwNjcgMTAzNywxMDY3IDExMDAsOTU0IDExMDAsNzI5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImwiIGhvcml6LWFkdi14PSI3MTciIGQ9Ik0gOTQyLDAgTCA1MTYsMCA1MTYsMTI3NiAyNDQsMTI3NiAyNDQsMTQxMSA2NzIsMTQxMSA2NzIsMTM1IDk0MiwxMzUgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iaSIgaG9yaXotYWR2LXg9IjUzMiIgZD0iTSA2ODIsMTQzNiBDIDcxMSwxNDM2IDczNiwxNDI2IDc1NywxNDA1IDc3OCwxMzg0IDc4OCwxMzU4IDc4OCwxMzI5IDc4OCwxMjk4IDc3OCwxMjczIDc1OCwxMjUyIDczNywxMjMxIDcxMiwxMjIxIDY4MiwxMjIxIDY1NSwxMjIxIDYzMSwxMjMyIDYxMCwxMjU0IDU4OSwxMjc1IDU3OCwxMzAwIDU3OCwxMzI5IDU3OCwxMzU4IDU4OSwxMzgzIDYxMCwxNDA0IDYzMSwxNDI1IDY1NSwxNDM2IDY4MiwxNDM2IFogTSA3NTgsMCBMIDYwMiwwIDYwMiw5MDcgMjk1LDkwNyAyOTUsMTA0MiA3NTgsMTA0MiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJoIiBob3Jpei1hZHYteD0iODMyIiBkPSJNIDEwMTgsMCBMIDg2MiwwIDg2Miw2NjggQyA4NjIsNzYxIDg0Nyw4MjggODE4LDg3MCA3ODgsOTExIDc0MSw5MzIgNjc4LDkzMiA1ODMsOTMyIDQ4MCw4NzMgMzcxLDc1NCBMIDM3MSwwIDIxNSwwIDIxNSwxNDExIDM3MSwxNDExIDM3MSw4ODkgQyA0ODMsMTAwOCA2MDEsMTA2NyA3MjUsMTA2NyA5MjAsMTA2NyAxMDE4LDk0OSAxMDE4LDcxMyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJnIiBob3Jpei1hZHYteD0iOTQ3IiBkPSJNIDUwNCwzOTMgQyA0MDcsMzQ2IDM1OCwzMDQgMzU4LDI2OCAzNTgsMjQ5IDM2OSwyMzIgMzkyLDIxOSA0MTQsMjA2IDQ0NCwxOTkgNDgzLDE5OSBMIDgzNCwyMDkgQyA5MTcsMjA5IDk4MiwxODkgMTAyOSwxNDggMTA3NSwxMDcgMTA5OCw1MCAxMDk4LC0yNSAxMDk4LC0xNDMgMTA0NSwtMjM3IDk0MCwtMzA3IDgzNSwtMzc3IDcxMiwtNDEyIDU3MSwtNDEyIDQ1NiwtNDEyIDM2MSwtMzg4IDI4OCwtMzQwIDIxNSwtMjkxIDE3OCwtMjI5IDE3OCwtMTUyIDE3OCwtNjkgMjM1LDIgMzQ4LDU5IDI1NCwxMDMgMjA3LDE1NyAyMDcsMjIxIDIwNywyOTQgMjY4LDM2MyAzODksNDI2IDMzMyw0NjAgMjkwLDUwNCAyNjAsNTU4IDIzMCw2MTIgMjE1LDY2OSAyMTUsNzI5IDIxNSw4MjcgMjQ4LDkwOCAzMTUsOTcxIDM4MiwxMDM0IDQ2NSwxMDY1IDU2NSwxMDY1IDYzNywxMDY1IDcwMiwxMDUwIDc2MCwxMDIwIEwgMTA2OSwxMDY3IDEwNjksOTE1IDg3MCw5MTUgQyA5MDAsODYyIDkxNSw4MDAgOTE1LDcyOSA5MTUsNjM0IDg4Miw1NTQgODE2LDQ4OSA3NTAsNDI0IDY2NiwzOTEgNTY1LDM5MSA1NDQsMzkxIDUyMywzOTIgNTA0LDM5MyBaIE0gNTY1LDUxMiBDIDYyMiw1MTIgNjY5LDUzMiA3MDYsNTcyIDc0Miw2MTEgNzYwLDY2MyA3NjAsNzI3IDc2MCw3OTQgNzQyLDg0NyA3MDcsODg1IDY3Miw5MjMgNjI0LDk0MiA1NjUsOTQyIDUxMCw5NDIgNDYzLDkyMiA0MjYsODgzIDM4OCw4NDMgMzY5LDc5MSAzNjksNzI3IDM2OSw2NjQgMzg4LDYxMyA0MjYsNTczIDQ2Myw1MzIgNTEwLDUxMiA1NjUsNTEyIFogTSA1NzEsLTI5MSBDIDY3NCwtMjkxIDc2MSwtMjY4IDgzNCwtMjIzIDkwNiwtMTc3IDk0MiwtMTIwIDk0MiwtNTMgOTQyLDE2IDg5MSw1MSA3ODgsNTEgNzU0LDUxIDY5NSw0OSA2MTIsNDUgNTU5LDQyIDUxOCw0MSA0ODgsNDEgNDczLDQxIDQ1MSwyOSA0MjAsNSAzODksLTE5IDM2NSwtNDMgMzQ5LC02OCAzMzIsLTkyIDMyNCwtMTE3IDMyNCwtMTQzIDMyNCwtMTg0IDM0NywtMjE5IDM5MiwtMjQ4IDQzNywtMjc3IDQ5NywtMjkxIDU3MSwtMjkxIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImYiIGhvcml6LWFkdi14PSI4MzIiIGQ9Ik0gMTAyOCwxMzk1IEwgOTg3LDEyNjggQyA5MTMsMTI4OSA4NDcsMTMwMCA3ODgsMTMwMCA3MzIsMTMwMCA2OTQsMTI4NCA2NzMsMTI1MyA2NTIsMTIyMSA2NDEsMTE1NyA2NDEsMTA2MSBMIDY0MSwxMDQyIDk5MSwxMDQyIDk5MSw5MDcgNjQxLDkwNyA2NDEsMCA0ODUsMCA0ODUsOTA3IDIyMyw5MDcgMjIzLDEwNDIgNDg1LDEwNDIgNDg1LDEwNzcgQyA0ODUsMTIxMCA1MDgsMTMwMyA1NTMsMTM1NiA1OTgsMTQwOSA2NzcsMTQzNiA3ODgsMTQzNiA4NjAsMTQzNiA5NDAsMTQyMiAxMDI4LDEzOTUgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZSIgaG9yaXotYWR2LXg9IjkwMSIgZD0iTSAxMDQ5LDUxNiBMIDMzNCw1MTYgQyAzMzQsMzk4IDM2OCwzMDEgNDM3LDIyNCA1MDUsMTQ3IDU4OSwxMDkgNjg4LDEwOSA3ODMsMTA5IDg4MCwxMjYgOTgxLDE2MCBMIDEwMDgsMzUgQyA5MTYsLTYgODA5LC0yNyA2ODYsLTI3IDUzMSwtMjcgNDA3LDIzIDMxMiwxMjIgMjE3LDIyMSAxNzAsMzU1IDE3MCw1MjQgMTcwLDY4OSAyMTMsODIxIDI5OCw5MjAgMzgzLDEwMTggNDkyLDEwNjcgNjI3LDEwNjcgNzQ3LDEwNjcgODQ3LDEwMjEgOTI4LDkzMCAxMDA5LDgzOSAxMDQ5LDcxNyAxMDQ5LDU2NSBaIE0gODc5LDY0MyBDIDg3OSw3MjQgODU0LDc5MyA4MDMsODQ5IDc1Miw5MDQgNjk2LDkzMiA2MzMsOTMyIDU1Miw5MzIgNDg2LDkwNSA0MzMsODUyIDM4MCw3OTkgMzUwLDcyOSAzNDQsNjQzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImQiIGhvcml6LWFkdi14PSI4NzgiIGQ9Ik0gMTAxNCwwIEwgODU4LDAgODU4LDE1MCBDIDgyNiw5OSA3ODAsNTYgNzIxLDIzIDY2MSwtMTAgNTk4LC0yNyA1MzIsLTI3IDQxMywtMjcgMzE4LDI2IDI0OSwxMzMgMTgwLDIzOSAxNDUsMzc0IDE0NSw1MzcgMTQ1LDY5MCAxODEsODE2IDI1NCw5MTcgMzI2LDEwMTcgNDIyLDEwNjcgNTQxLDEwNjcgNjcyLDEwNjcgNzc4LDEwMTQgODU4LDkwNyBMIDg1OCwxNDExIDEwMTQsMTQxMSBaIE0gODU4LDMxOSBMIDg1OCw3NTIgQyA3ODMsODcyIDY5Myw5MzIgNTg4LDkzMiA1MDIsOTMyIDQzNCw4OTQgMzg0LDgxOCAzMzQsNzQxIDMwOSw2NDUgMzA5LDUyOCAzMDksNDA4IDMzMywzMDkgMzgwLDIzMSA0MjcsMTUyIDQ5MSwxMTMgNTczLDExMyA2MTIsMTEzIDY1MiwxMjQgNjkyLDE0NSA3MzIsMTY2IDc3MCwxOTggODA1LDI0MSA4NDAsMjg0IDg1OCwzMTAgODU4LDMxOSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJjIiBob3Jpei1hZHYteD0iODU1IiBkPSJNIDEwMTQsMzUgQyA5MjMsLTYgODI0LC0yNyA3MTUsLTI3IDU1MSwtMjcgNDIwLDIzIDMyMywxMjMgMjI1LDIyMiAxNzYsMzU1IDE3Niw1MjAgMTc2LDY4NSAyMjUsODE4IDMyNCw5MTggNDIyLDEwMTcgNTUxLDEwNjcgNzExLDEwNjcgODE2LDEwNjcgOTE1LDEwNDkgMTAxMCwxMDEyIEwgOTY5LDg4MyBDIDg3NCw5MTYgNzg5LDkzMiA3MTMsOTMyIDU5OCw5MzIgNTA4LDg5NSA0NDEsODIxIDM3NCw3NDcgMzQwLDY0NyAzNDAsNTIwIDM0MCw0MDQgMzc1LDMwNyA0NDQsMjI4IDUxMywxNDkgNjA0LDEwOSA3MTUsMTA5IDgwMywxMDkgODk0LDEyNiA5ODcsMTYwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImIiIGhvcml6LWFkdi14PSI4NzgiIGQ9Ik0gMzcxLDkwNyBDIDQwNiw5NTUgNDUzLDk5NCA1MTIsMTAyMyA1NzEsMTA1MiA2MzMsMTA2NyA2OTgsMTA2NyA4MTksMTA2NyA5MTQsMTAxOCA5ODIsOTIxIDEwNDksODI0IDEwODMsNjk2IDEwODMsNTM3IDEwODMsMzc2IDEwNDcsMjQyIDk3NSwxMzUgOTAzLDI3IDgwNywtMjcgNjg2LC0yNyA2MjEsLTI3IDU2MSwtMTEgNTA1LDIyIDQ0OCw1NCA0MDQsOTcgMzcxLDE1MCBMIDM3MSwwIDIxNSwwIDIxNSwxNDExIDM3MSwxNDExIFogTSAzNzEsNzUyIEwgMzcxLDMxOSBDIDQwMCwyNjAgNDQwLDIxMSA0OTAsMTcyIDU0MCwxMzMgNTkyLDExMyA2NDUsMTEzIDcyOSwxMTMgNzk2LDE1MyA4NDYsMjMzIDg5NSwzMTMgOTIwLDQxMSA5MjAsNTI4IDkyMCw2NDggODk2LDc0NSA4NDksODIwIDgwMiw4OTUgNzM2LDkzMiA2NTEsOTMyIDU0MSw5MzIgNDQ4LDg3MiAzNzEsNzUyIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImEiIGhvcml6LWFkdi14PSI4NTUiIGQ9Ik0gMTA0NCwwIEwgODg5LDAgQyA4NzYsNDIgODcwLDk4IDg3MCwxNjggNzgxLDM4IDY2MSwtMjcgNTA4LC0yNyA0MzEsLTI3IDM2MywxIDMwNCw1NiAyNDUsMTExIDIxNSwxODEgMjE1LDI2OCAyMTUsMzE5IDIyNSwzNjUgMjQ2LDQwNCAyNjcsNDQzIDI5Nyw0NzcgMzM3LDUwNCAzNzcsNTMxIDQyNiw1NTIgNDgzLDU2OCA1NDAsNTgzIDYwOCw1OTQgNjg3LDYwMCBMIDg2Miw2MTQgODYyLDY2NCBDIDg2Miw4NDMgNzc5LDkzMiA2MTIsOTMyIDU3Myw5MzIgNTIzLDkyNCA0NjMsOTA3IDQwMiw4OTAgMzUwLDg2OSAzMDcsODQ2IEwgMjY2LDk1OCBDIDQwOCwxMDMxIDU0MCwxMDY3IDY2MiwxMDY3IDg5OSwxMDY3IDEwMTgsOTQxIDEwMTgsNjkwIEwgMTAxOCwyNDIgQyAxMDE4LDE1MSAxMDI3LDcxIDEwNDQsMCBaIE0gODYyLDMwOSBMIDg2Miw0OTggQyA3MjEsNDg1IDYyNSw0NzQgNTczLDQ2MyA1MjAsNDUyIDQ3NSw0MzEgNDM3LDQwMSAzOTgsMzcxIDM3OSwzMzAgMzc5LDI3OSAzNzksMjMyIDM5NSwxOTUgNDI4LDE2NiA0NjEsMTM3IDUwMiwxMjMgNTUzLDEyMyA2MDgsMTIzIDY2MywxNDAgNzE4LDE3MyA3NzMsMjA2IDgyMSwyNTEgODYyLDMwOSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJfIiBob3Jpei1hZHYteD0iMTI0OCIgZD0iTSAxMjI5LC00NDYgTCAwLC00NDYgMCwtMzE1IDEyMjksLTMxNSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJTIiBob3Jpei1hZHYteD0iODMyIiBkPSJNIDkxNSwxMjU1IEMgODMxLDEyODQgNzUxLDEyOTggNjc2LDEyOTggNTkxLDEyOTggNTIxLDEyNzkgNDY4LDEyNDAgNDE0LDEyMDEgMzg3LDExNDUgMzg3LDEwNzMgMzg3LDk4OCA0NTcsOTEzIDU5OCw4NDggNzAyLDgwMCA3NzEsNzY3IDgwNSw3NDggODM4LDcyOSA4NzIsNzA0IDkwNyw2NzEgOTQyLDYzOCA5NzAsNjAwIDk5MSw1NTYgMTAxMiw1MTEgMTAyMiw0NjEgMTAyMiw0MDYgMTAyMiwyNjkgOTc2LDE2MiA4ODQsODcgNzkxLDExIDY4MiwtMjcgNTU3LC0yNyA0MzYsLTI3IDMyOSwtMyAyMzgsNDUgTCAyNzIsMTg2IEMgMzc5LDEzNyA0NzMsMTEzIDU1MywxMTMgNjQ2LDExMyA3MjEsMTM3IDc3OCwxODYgODM0LDIzNCA4NjIsMzAwIDg2MiwzODUgODYyLDQ0OCA4NDQsNTAxIDgwNyw1NDUgNzcwLDU4OCA3MDMsNjMzIDYwNCw2NzggNTAzLDcyNSA0MzcsNzU3IDQwOCw3NzQgMzc4LDc5MSAzNDgsODE0IDMxOSw4NDQgMjkwLDg3NCAyNjcsOTA4IDI1MSw5NDUgMjM1LDk4MiAyMjcsMTAyMyAyMjcsMTA2NyAyMjcsMTE4NCAyNjksMTI3NSAzNTMsMTM0MCA0MzcsMTQwNSA1NDMsMTQzOCA2NzIsMTQzOCA3NjYsMTQzOCA4NTksMTQyNCA5NTIsMTM5NSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJPIiBob3Jpei1hZHYteD0iOTk0IiBkPSJNIDYxMiwxNDM4IEMgNzcwLDE0MzggODkxLDEzNzYgOTc0LDEyNTEgMTA1NywxMTI2IDEwOTgsOTQ0IDEwOTgsNzA1IDEwOTgsNDY4IDEwNTcsMjg2IDk3NCwxNjEgODkxLDM2IDc3MSwtMjcgNjEyLC0yNyA0NTUsLTI3IDMzNSwzNiAyNTIsMTYxIDE2OSwyODYgMTI3LDQ2NyAxMjcsNzA1IDEyNyw5NDMgMTY4LDExMjUgMjUxLDEyNTAgMzM0LDEzNzUgNDU0LDE0MzggNjEyLDE0MzggWiBNIDYxMiwxMTcgQyA4MjAsMTE3IDkyNCwzMTMgOTI0LDcwNSA5MjQsMTA5OCA4MjAsMTI5NCA2MTIsMTI5NCA1MTEsMTI5NCA0MzQsMTI0MyAzODEsMTE0MiAzMjgsMTA0MCAzMDEsODk0IDMwMSw3MDUgMzAxLDUxNCAzMjcsMzY4IDM4MCwyNjggNDMzLDE2NyA1MTAsMTE3IDYxMiwxMTcgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iSSIgaG9yaXotYWR2LXg9IjY3MSIgZD0iTSA5MjgsMCBMIDI4NywwIDI4NywxNDMgNTMwLDE0MyA1MzAsMTI2OCAyODcsMTI2OCAyODcsMTQxMSA5MjgsMTQxMSA5MjgsMTI2OCA2OTQsMTI2OCA2OTQsMTQzIDkyOCwxNDMgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iRyIgaG9yaXotYWR2LXg9Ijk3MSIgZD0iTSAxMDY5LDEwMCBDIDk1NCwxNSA4MjYsLTI3IDY4NiwtMjcgNTAzLC0yNyAzNjQsMzUgMjY5LDE2MCAxNzQsMjg0IDEyNyw0NjYgMTI3LDcwNSAxMjcsOTM2IDE3MywxMTE2IDI2NSwxMjQ1IDM1NiwxMzc0IDQ5NywxNDM4IDY4NiwxNDM4IDc1NCwxNDM4IDgyMSwxNDI5IDg4OCwxNDEyIDk1NSwxMzk1IDEwMDgsMTM3MiAxMDQ5LDEzNDMgTCA5OTUsMTIxNCBDIDkwMSwxMjY3IDc5OCwxMjk0IDY4NiwxMjk0IDU1NywxMjk0IDQ2MCwxMjQ3IDM5NywxMTUyIDMzMywxMDU3IDMwMSw5MDggMzAxLDcwNSAzMDEsMzEzIDQyOSwxMTcgNjg2LDExNyA3ODMsMTE3IDg1NiwxMzcgOTA1LDE3OCBMIDkwNSw1MjYgNjUxLDUyNiA2NTEsNjY4IDEwNjksNjY4IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IkMiIGhvcml6LWFkdi14PSI5NzEiIGQ9Ik0gMTA3MSw4OCBDIDk2MCwxMSA4MzIsLTI3IDY4NiwtMjcgNTAzLC0yNyAzNjUsMzYgMjcwLDE2MSAxNzUsMjg2IDEyNyw0NjggMTI3LDcwNSAxMjcsOTM0IDE3MywxMTE0IDI2NSwxMjQ0IDM1NywxMzczIDQ5NywxNDM4IDY4NiwxNDM4IDc1NCwxNDM4IDgyMSwxNDI5IDg4OCwxNDEyIDk1NSwxMzk1IDEwMDgsMTM3MiAxMDQ5LDEzNDMgTCA5OTUsMTIxNCBDIDkwMSwxMjY3IDc5OCwxMjk0IDY4NiwxMjk0IDU1NiwxMjk0IDQ1OSwxMjQ3IDM5NiwxMTUzIDMzMywxMDU4IDMwMSw5MDkgMzAxLDcwNSAzMDEsMzEzIDQyOSwxMTcgNjg2LDExNyA3OTUsMTE3IDkwNiwxNTAgMTAyMCwyMTcgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iOiIgaG9yaXotYWR2LXg9IjI1NSIgZD0iTSA2MDYsMTA1NyBDIDY0MCwxMDU3IDY2OSwxMDQ2IDY5MiwxMDIzIDcxNSwxMDAwIDcyNyw5NzEgNzI3LDkzOCA3MjcsOTA1IDcxNSw4NzcgNjkyLDg1NCA2NjksODMxIDY0MCw4MTkgNjA2LDgxOSA1NzMsODE5IDU0NSw4MzEgNTIyLDg1NCA0OTksODc3IDQ4Nyw5MDUgNDg3LDkzOCA0ODcsOTcxIDQ5OSwxMDAwIDUyMiwxMDIzIDU0NSwxMDQ2IDU3MywxMDU3IDYwNiwxMDU3IFogTSA2MDYsMjIxIEMgNjQwLDIyMSA2NjksMjEwIDY5MiwxODcgNzE1LDE2NCA3MjcsMTM2IDcyNywxMDIgNzI3LDY5IDcxNSw0MSA2OTIsMTggNjY5LC01IDY0MCwtMTYgNjA2LC0xNiA1NzMsLTE2IDU0NSwtNSA1MjIsMTggNDk5LDQxIDQ4Nyw2OSA0ODcsMTAyIDQ4NywxMzYgNDk5LDE2NCA1MjIsMTg3IDU0NSwyMTAgNTczLDIyMSA2MDYsMjIxIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjkiIGhvcml6LWFkdi14PSI5MDEiIGQ9Ik0gODgzLDY3OCBDIDgwMiw2MDcgNzA2LDU3MSA1OTYsNTcxIDQ3Nyw1NzEgMzc3LDYwOSAyOTgsNjg2IDIxOCw3NjIgMTc4LDg2MCAxNzgsOTgxIDE3OCwxMTEwIDIxNiwxMjE5IDI5MywxMzA4IDM2OSwxMzk2IDQ2NywxNDQwIDU4OCwxNDQwIDczNSwxNDQwIDg1MCwxMzczIDkzMywxMjM4IDEwMTYsMTEwMyAxMDU3LDkzMiAxMDU3LDcyNSAxMDU3LDUxOCAxMDA5LDM0MSA5MTIsMTk1IDgxNSw0OCA2ODUsLTI1IDUyMiwtMjUgNDEzLC0yNSAzMTgsLTggMjM4LDI1IEwgMjY0LDE3MCBDIDM1NiwxMzYgNDQxLDExOSA1MTgsMTE5IDYyOSwxMTkgNzE3LDE3MyA3ODQsMjgwIDg1MCwzODcgODgzLDUyMCA4ODMsNjc4IFogTSA4NzksODE5IEMgODc5LDk0NiA4NTMsMTA1NyA4MDIsMTE1MyA3NTEsMTI0OCA2ODMsMTI5NiA2MDAsMTI5NiA1MjUsMTI5NiA0NjUsMTI2OCA0MjAsMTIxMyAzNzUsMTE1NyAzNTIsMTA4NCAzNTIsOTk1IDM1Miw5MTMgMzc1LDg0NiA0MjEsNzk0IDQ2Niw3NDEgNTI2LDcxNSA2MDAsNzE1IDcwMiw3MTUgNzkxLDc0NiA4NjYsODA5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjgiIGhvcml6LWFkdi14PSI5MDEiIGQ9Ik0gNzU2LDc3NCBDIDg1Miw3NDYgOTI3LDY5OCA5ODAsNjMxIDEwMzMsNTY0IDEwNTksNDg1IDEwNTksMzk1IDEwNTksMjY2IDEwMTYsMTYzIDkzMSw4NyA4NDUsMTEgNzM5LC0yNyA2MTQsLTI3IDQ4MywtMjcgMzc1LDExIDI5MSw4OCAyMDYsMTY1IDE2NCwyNjYgMTY0LDM5MyAxNjQsNDgyIDE5MSw1NjEgMjQ2LDYyOSAzMDEsNjk3IDM3Nyw3NDUgNDc1LDc3NCAzOTcsNzk0IDMzNSw4MzMgMjg4LDg5MCAyNDEsOTQ3IDIxNywxMDEwIDIxNywxMDgxIDIxNywxMTcyIDI1MywxMjU0IDMyNSwxMzI4IDM5NywxNDAxIDQ5MiwxNDM4IDYxMCwxNDM4IDcyNywxNDM4IDgyMywxNDAxIDkwMCwxMzI4IDk3NiwxMjU1IDEwMTQsMTE3MiAxMDE0LDEwODEgMTAxNCwxMDEyIDk5MCw5NDkgOTQyLDg5MiA4OTQsODM0IDgzMiw3OTUgNzU2LDc3NCBaIE0gNjEwLDg0MiBDIDYxMyw4NDIgNjI5LDg0NiA2NTgsODU0IDY4Nyw4NjIgNzExLDg3MiA3MzIsODg0IDc1Miw4OTUgNzcxLDkxMCA3ODgsOTI4IDgwNSw5NDYgODE5LDk2NyA4MzAsOTkyIDg0MSwxMDE2IDg0NiwxMDQzIDg0NiwxMDczIDg0NiwxMTM3IDgyMywxMTkwIDc3NywxMjMzIDczMSwxMjc1IDY3NSwxMjk2IDYxMCwxMjk2IDU0NywxMjk2IDQ5MiwxMjc0IDQ0NywxMjMxIDQwMiwxMTg4IDM3OSwxMTM1IDM3OSwxMDczIDM3OSwxMDM0IDM4Nyw5OTkgNDA0LDk3MCA0MjAsOTQxIDQ0MCw5MTcgNDY1LDg5OSA0ODksODgxIDUxNyw4NjcgNTQ5LDg1NyA1ODAsODQ3IDYwMSw4NDIgNjEwLDg0MiBaIE0gNjEwLDcwMiBDIDQyNyw2NDkgMzM2LDU0OCAzMzYsNDAxIDMzNiwzMTUgMzY0LDI0NiA0MTksMTk1IDQ3NCwxNDMgNTM5LDExNyA2MTQsMTE3IDY5MSwxMTcgNzU3LDE0MyA4MTAsMTk0IDg2MywyNDUgODg5LDMxNCA4ODksNDAxIDg4OSw0NjcgODcxLDUyNiA4MzQsNTc5IDc5Nyw2MzEgNzIzLDY3MiA2MTAsNzAyIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjciIGhvcml6LWFkdi14PSI4NTUiIGQ9Ik0gMTAzNiwxMjk0IEwgNDc3LDAgMzA5LDAgODYwLDEyNjggMTk5LDEyNjggMTk5LDE0MTEgMTAzNiwxNDExIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjYiIGhvcml6LWFkdi14PSI5MDEiIGQ9Ik0gMzYyLDczNSBDIDQ1MCw4MDYgNTQ2LDg0MiA2NDksODQyIDc2OCw4NDIgODY4LDgwNCA5NDgsNzI4IDEwMjcsNjUxIDEwNjcsNTUzIDEwNjcsNDMyIDEwNjcsMjk5IDEwMzIsMTkwIDk2MSwxMDMgODkwLDE2IDc5NiwtMjcgNjc4LC0yNyA1MjcsLTI3IDQwOCw0MiAzMjAsMTgxIDIzMiwzMTkgMTg4LDQ4OCAxODgsNjg4IDE4OCw5MDEgMjM0LDEwODAgMzI2LDEyMjMgNDE3LDEzNjYgNTQzLDE0MzggNzAyLDE0MzggODEyLDE0MzggOTA3LDE0MjIgOTg3LDEzODkgTCA5NjEsMTI0MyBDIDg2OCwxMjc3IDc4NCwxMjk0IDcwNywxMjk0IDU5OCwxMjk0IDUxNCwxMjQyIDQ1MywxMTM4IDM5MiwxMDM0IDM2Miw5MDAgMzYyLDczNSBaIE0gMzY3LDU5NCBDIDM2Nyw0NzMgMzk1LDM2NCA0NTIsMjY1IDUwOSwxNjYgNTgwLDExNyA2NjYsMTE3IDczOSwxMTcgNzk1LDE0NCA4MzQsMTk3IDg3MywyNTAgODkzLDMyNCA4OTMsNDE4IDg5Myw1MDUgODcxLDU3MyA4MjgsNjIzIDc4NSw2NzMgNzI0LDY5OCA2NDUsNjk4IDU0Myw2OTggNDUwLDY2MyAzNjcsNTk0IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjUiIGhvcml6LWFkdi14PSI4MDkiIGQ9Ik0gNDM0LDg0MiBDIDQ3OSw4NTQgNTI0LDg2MCA1NjcsODYwIDcwMCw4NjAgODA2LDgxOSA4ODMsNzM4IDk2MCw2NTYgOTk5LDU1NCA5OTksNDMyIDk5OSwzMDQgOTU0LDE5NiA4NjQsMTA3IDc3NCwxOCA2NjQsLTI3IDUzNSwtMjcgNDEyLC0yNyAzMDgsLTYgMjI1LDM1IEwgMjU4LDE3NCBDIDM0OSwxMzYgNDQyLDExNyA1MzksMTE3IDYyNSwxMTcgNjk1LDE0NSA3NDgsMjAwIDgwMSwyNTUgODI3LDMzMyA4MjcsNDMyIDgyNyw1MjYgODAyLDU5OCA3NTIsNjQ5IDcwMiw3MDAgNjM4LDcyNSA1NTksNzI1IDUwMiw3MjUgNDQ1LDcxNCAzODksNjkyIEwgMjkxLDczNyAyOTEsMTQxMSA5MzYsMTQxMSA5MzYsMTI2OCA0MzQsMTI2OCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSI0IiBob3Jpei1hZHYteD0iOTcxIiBkPSJNIDEwNzMsMzI2IEwgODk5LDMyNiA4OTksMCA3MzUsMCA3MzUsMzI2IDEyMSwzMjYgMTIxLDQ1MSA2NzgsMTQxMSA4OTksMTQxMSA4OTksNDY5IDEwNzMsNDY5IFogTSA3MzUsNDY5IEwgNzM1LDEyNDkgMjg3LDQ2OSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSIzIiBob3Jpei1hZHYteD0iODA5IiBkPSJNIDcwMiw3NTggQyA3ODgsNzM0IDg1Nyw2OTAgOTA5LDYyNyA5NjEsNTYzIDk4Nyw0OTEgOTg3LDQxMiA5ODcsMjg4IDkzOSwxODQgODQyLDEwMCA3NDUsMTUgNjMxLC0yNyA1MDAsLTI3IDM4OCwtMjcgMjg4LC0yIDE5OSw0NyBMIDIzMSwxODIgQyAzMjQsMTM5IDQxNSwxMTcgNTA2LDExNyA1OTEsMTE3IDY2NCwxNDQgNzI1LDE5NyA3ODUsMjUwIDgxNSwzMTggODE1LDQwMSA4MTUsNDgzIDc4Myw1NTAgNzE4LDYwMSA2NTMsNjUyIDU2OCw2NzggNDYxLDY3OCBMIDQxOCw2NzggNDE4LDgxMyA0MzAsODEzIEMgNTMzLDgxMyA2MTcsODM3IDY4NCw4ODYgNzUxLDkzNCA3ODQsOTk5IDc4NCwxMDgxIDc4NCwxMTQ4IDc2MCwxMjAwIDcxMiwxMjM4IDY2NCwxMjc1IDU5OCwxMjk0IDUxNCwxMjk0IDQ0MSwxMjk0IDM2MCwxMjc0IDI3MCwxMjM1IEwgMjQ4LDEzNzYgQyAzMzksMTQxNyA0MzAsMTQzOCA1MjIsMTQzOCA2NDgsMTQzOCA3NTIsMTQwNSA4MzQsMTMzOSA5MTUsMTI3MiA5NTYsMTE4NyA5NTYsMTA4MyA5NTYsMTAxMSA5MzEsOTQ0IDg4Miw4ODIgODMyLDgxOSA3NzIsNzgwIDcwMiw3NjQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iMiIgaG9yaXotYWR2LXg9IjgwOSIgZD0iTSA5ODEsMCBMIDIwNSwwIDIwNSwxMTkgQyA0MDIsMzM4IDUyOSw0ODUgNTg4LDU2MCA2NDcsNjM1IDY5NCw3MTMgNzMxLDc5NSA3NjgsODc3IDc4Niw5NTkgNzg2LDEwNDAgNzg2LDExMTkgNzYyLDExODEgNzE1LDEyMjYgNjY3LDEyNzEgNjAwLDEyOTQgNTE0LDEyOTQgNDQ4LDEyOTQgMzU5LDEyNzQgMjQ4LDEyMzMgTCAyMjUsMTM3NCBDIDMyNiwxNDE3IDQzMSwxNDM4IDU0MSwxNDM4IDY2NywxNDM4IDc2OSwxNDAzIDg0NiwxMzMyIDkyMywxMjYxIDk2MSwxMTY2IDk2MSwxMDQ3IDk2MSw5NjIgOTQxLDg3OCA5MDIsNzk1IDg2Miw3MTIgODExLDYzMiA3NDksNTU1IDY4Nyw0NzggNTY2LDM0MSAzODcsMTQzIEwgOTgxLDE0MyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSIxIiBob3Jpei1hZHYteD0iNzg2IiBkPSJNIDEwMTgsMCBMIDI3NiwwIDI3NiwxNDMgNTc1LDE0MyA1NzUsMTIzOSAyOTMsMTA5OCAyOTMsMTI2MiA1OTgsMTQxOSA3MzksMTQxOSA3MzksMTQzIDEwMTgsMTQzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IjAiIGhvcml6LWFkdi14PSI5NDciIGQ9Ik0gNjEyLDE0MzggQyA3NjEsMTQzOCA4NzYsMTM3NCA5NTcsMTI0NiAxMDM3LDExMTcgMTA3Nyw5MzcgMTA3Nyw3MDUgMTA3Nyw0ODAgMTAzOCwzMDIgOTYwLDE3MSA4ODIsMzkgNzY2LC0yNyA2MTIsLTI3IDQ2MywtMjcgMzQ5LDM3IDI2OCwxNjUgMTg3LDI5MyAxNDcsNDczIDE0Nyw3MDUgMTQ3LDkzMCAxODYsMTEwOSAyNjUsMTI0MSAzNDMsMTM3MiA0NTksMTQzOCA2MTIsMTQzOCBaIE0gNjEyLDExNyBDIDcxMSwxMTcgNzg0LDE2NCA4MzIsMjU4IDg3OSwzNTEgOTAzLDUwMCA5MDMsNzA1IDkwMyw5MTAgODc5LDEwNjAgODMyLDExNTMgNzg0LDEyNDYgNzExLDEyOTIgNjEyLDEyOTIgNTE1LDEyOTIgNDQyLDEyNDQgMzk0LDExNDkgMzQ2LDEwNTQgMzIyLDkwNiAzMjIsNzA1IDMyMiw1MDIgMzQ2LDM1MyAzOTQsMjU5IDQ0MSwxNjQgNTE0LDExNyA2MTIsMTE3IFogTSA2MDgsODU1IEMgNjUwLDg1NSA2ODYsODQxIDcxNSw4MTIgNzQ0LDc4MyA3NTgsNzQ3IDc1OCw3MDUgNzU4LDY2MiA3NDMsNjI1IDcxNCw1OTQgNjg1LDU2MyA2NDksNTQ3IDYwOCw1NDcgNTY3LDU0NyA1MzMsNTYzIDUwNCw1OTQgNDc1LDYyNSA0NjAsNjYyIDQ2MCw3MDUgNDYwLDc0OCA0NzQsNzgzIDUwMyw4MTIgNTMxLDg0MSA1NjYsODU1IDYwOCw4NTUgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iKyIgaG9yaXotYWR2LXg9Ijk3MCIgZD0iTSAxMDg3LDY1NyBMIDY4Miw2NTcgNjgyLDI0OCA1NTEsMjQ4IDU1MSw2NTcgMTQxLDY1NyAxNDEsNzg4IDU1MSw3ODggNTUxLDExOTggNjgyLDExOTggNjgyLDc4OCAxMDg3LDc4OCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSIgIiBob3Jpei1hZHYteD0iMTIyNCIvPgogIDwvZm9udD4KIDwvZGVmcz4KIDxkZWZzPgogIDxmb250IGlkPSJFbWJlZGRlZEZvbnRfMiIgaG9yaXotYWR2LXg9IjIwNDgiPgogICA8Zm9udC1mYWNlIGZvbnQtZmFtaWx5PSJBcmlhbCBlbWJlZGRlZCIgdW5pdHMtcGVyLWVtPSIyMDQ4IiBmb250LXdlaWdodD0ibm9ybWFsIiBmb250LXN0eWxlPSJub3JtYWwiIGFzY2VudD0iMTg3MCIgZGVzY2VudD0iNDM5Ii8+CiAgIDxtaXNzaW5nLWdseXBoIGhvcml6LWFkdi14PSIyMDQ4IiBkPSJNIDAsMCBMIDIwNDcsMCAyMDQ3LDIwNDcgMCwyMDQ3IDAsMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSLigK8iIGhvcml6LWFkdi14PSIxIi8+CiAgIDxnbHlwaCB1bmljb2RlPSLCuyIgaG9yaXotYWR2LXg9Ijg3OCIgZD0iTSA3OTEsNTI4IEwgNTMwLDk4NCA2NzgsOTg0IDk5Nyw1MjggNjc4LDcyIDUzMSw3MiBaIE0gNDA0LDUyOCBMIDE0MCw5ODQgMjkxLDk4NCA2MDUsNTI4IDI5MSw3MiAxNDAsNzIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iwqsiIGhvcml6LWFkdi14PSI4NzkiIGQ9Ik0gMzQwLDUyOCBMIDU5OSw3MiA0NTMsNzIgMTM0LDUyOCA0NTMsOTg0IDYwMSw5ODQgWiBNIDcyNyw1MjggTCA5OTEsNzIgODM5LDcyIDUyNiw1MjggODM5LDk4NCA5OTEsOTg0IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InUiIGhvcml6LWFkdi14PSI4NzkiIGQ9Ik0gODMxLDAgTCA4MzEsMTU2IEMgNzQ4LDM2IDYzNiwtMjQgNDk0LC0yNCA0MzEsLTI0IDM3MywtMTIgMzE5LDEyIDI2NCwzNiAyMjQsNjYgMTk4LDEwMyAxNzEsMTM5IDE1MywxODMgMTQyLDIzNiAxMzUsMjcxIDEzMSwzMjcgMTMxLDQwNCBMIDEzMSwxMDYyIDMxMSwxMDYyIDMxMSw0NzMgQyAzMTEsMzc5IDMxNSwzMTYgMzIyLDI4MyAzMzMsMjM2IDM1NywxOTkgMzk0LDE3MiA0MzEsMTQ1IDQ3NiwxMzEgNTMwLDEzMSA1ODQsMTMxIDYzNSwxNDUgNjgyLDE3MyA3MjksMjAwIDc2MywyMzggNzgzLDI4NiA4MDIsMzMzIDgxMiw0MDIgODEyLDQ5MyBMIDgxMiwxMDYyIDk5MiwxMDYyIDk5MiwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InQiIGhvcml6LWFkdi14PSI1MzIiIGQ9Ik0gNTI4LDE2MSBMIDU1NCwyIEMgNTAzLC05IDQ1OCwtMTQgNDE4LC0xNCAzNTMsLTE0IDMwMiwtNCAyNjYsMTcgMjMwLDM4IDIwNSw2NSAxOTAsOTkgMTc1LDEzMiAxNjgsMjAzIDE2OCwzMTEgTCAxNjgsOTIyIDM2LDkyMiAzNiwxMDYyIDE2OCwxMDYyIDE2OCwxMzI1IDM0NywxNDMzIDM0NywxMDYyIDUyOCwxMDYyIDUyOCw5MjIgMzQ3LDkyMiAzNDcsMzAxIEMgMzQ3LDI1MCAzNTAsMjE3IDM1NywyMDIgMzYzLDE4NyAzNzMsMTc2IDM4OCwxNjcgNDAyLDE1OCA0MjIsMTU0IDQ0OSwxNTQgNDY5LDE1NCA0OTUsMTU2IDUyOCwxNjEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iciIgaG9yaXotYWR2LXg9IjYwMiIgZD0iTSAxMzMsMCBMIDEzMywxMDYyIDI5NSwxMDYyIDI5NSw5MDEgQyAzMzYsOTc2IDM3NSwxMDI2IDQxMCwxMDUwIDQ0NSwxMDc0IDQ4MywxMDg2IDUyNSwxMDg2IDU4NiwxMDg2IDY0NywxMDY3IDcxMCwxMDI4IEwgNjQ4LDg2MSBDIDYwNCw4ODcgNTYwLDkwMCA1MTYsOTAwIDQ3Nyw5MDAgNDQxLDg4OCA0MTAsODY1IDM3OSw4NDEgMzU2LDgwOCAzNDMsNzY2IDMyMyw3MDIgMzEzLDYzMiAzMTMsNTU2IEwgMzEzLDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibyIgaG9yaXotYWR2LXg9IjEwNDAiIGQ9Ik0gNjgsNTMxIEMgNjgsNzI4IDEyMyw4NzMgMjMyLDk2OCAzMjMsMTA0NyA0MzUsMTA4NiA1NjYsMTA4NiA3MTIsMTA4NiA4MzEsMTAzOCA5MjQsOTQzIDEwMTcsODQ3IDEwNjMsNzE1IDEwNjMsNTQ2IDEwNjMsNDA5IDEwNDMsMzAyIDEwMDIsMjI0IDk2MSwxNDUgOTAxLDg0IDgyMyw0MSA3NDQsLTIgNjU5LC0yNCA1NjYsLTI0IDQxNywtMjQgMjk3LDI0IDIwNiwxMTkgMTE0LDIxNCA2OCwzNTIgNjgsNTMxIFogTSAyNTMsNTMxIEMgMjUzLDM5NSAyODMsMjkzIDM0MiwyMjYgNDAxLDE1OCA0NzYsMTI0IDU2NiwxMjQgNjU1LDEyNCA3MzAsMTU4IDc4OSwyMjYgODQ4LDI5NCA4NzgsMzk4IDg3OCw1MzcgODc4LDY2OCA4NDgsNzY4IDc4OSw4MzYgNzI5LDkwMyA2NTUsOTM3IDU2Niw5MzcgNDc2LDkzNyA0MDEsOTAzIDM0Miw4MzYgMjgzLDc2OSAyNTMsNjY3IDI1Myw1MzEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibiIgaG9yaXotYWR2LXg9IjkwMiIgZD0iTSAxMzUsMCBMIDEzNSwxMDYyIDI5NywxMDYyIDI5Nyw5MTEgQyAzNzUsMTAyOCA0ODgsMTA4NiA2MzUsMTA4NiA2OTksMTA4NiA3NTgsMTA3NSA4MTIsMTA1MiA4NjUsMTAyOSA5MDUsOTk4IDkzMiw5NjEgOTU5LDkyNCA5NzcsODc5IDk4OCw4MjggOTk1LDc5NSA5OTgsNzM2IDk5OCw2NTMgTCA5OTgsMCA4MTgsMCA4MTgsNjQ2IEMgODE4LDcxOSA4MTEsNzc0IDc5Nyw4MTEgNzgzLDg0NyA3NTgsODc2IDcyMyw4OTggNjg3LDkxOSA2NDUsOTMwIDU5Nyw5MzAgNTIwLDkzMCA0NTQsOTA2IDM5OSw4NTcgMzQzLDgwOCAzMTUsNzE2IDMxNSw1ODAgTCAzMTUsMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJtIiBob3Jpei1hZHYteD0iMTQ3OSIgZD0iTSAxMzUsMCBMIDEzNSwxMDYyIDI5NiwxMDYyIDI5Niw5MTMgQyAzMjksOTY1IDM3NCwxMDA3IDQyOSwxMDM5IDQ4NCwxMDcwIDU0NywxMDg2IDYxOCwxMDg2IDY5NywxMDg2IDc2MSwxMDcwIDgxMiwxMDM3IDg2MiwxMDA0IDg5Nyw5NTkgOTE4LDkwMCAxMDAyLDEwMjQgMTExMSwxMDg2IDEyNDYsMTA4NiAxMzUxLDEwODYgMTQzMiwxMDU3IDE0ODksOTk5IDE1NDYsOTQwIDE1NzQsODUwIDE1NzQsNzI5IEwgMTU3NCwwIDEzOTUsMCAxMzk1LDY2OSBDIDEzOTUsNzQxIDEzODksNzkzIDEzNzgsODI1IDEzNjYsODU2IDEzNDUsODgyIDEzMTQsOTAxIDEyODMsOTIwIDEyNDcsOTMwIDEyMDYsOTMwIDExMzEsOTMwIDEwNjksOTA1IDEwMjAsODU2IDk3MSw4MDYgOTQ2LDcyNiA5NDYsNjE3IEwgOTQ2LDAgNzY2LDAgNzY2LDY5MCBDIDc2Niw3NzAgNzUxLDgzMCA3MjIsODcwIDY5Myw5MTAgNjQ1LDkzMCA1NzgsOTMwIDUyNyw5MzAgNDgxLDkxNyA0MzgsODkwIDM5NSw4NjMgMzYzLDgyNCAzNDQsNzczIDMyNSw3MjIgMzE1LDY0OCAzMTUsNTUxIEwgMzE1LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iaSIgaG9yaXotYWR2LXg9IjIwOSIgZD0iTSAxMzYsMTI1OSBMIDEzNiwxNDY2IDMxNiwxNDY2IDMxNiwxMjU5IFogTSAxMzYsMCBMIDEzNiwxMDYyIDMxNiwxMDYyIDMxNiwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImUiIGhvcml6LWFkdi14PSI5OTQiIGQ9Ik0gODYyLDM0MiBMIDEwNDgsMzE5IEMgMTAxOSwyMTAgOTY0LDEyNiA4ODUsNjYgODA2LDYgNzA0LC0yNCA1ODEsLTI0IDQyNiwtMjQgMzAzLDI0IDIxMiwxMjAgMTIxLDIxNSA3NSwzNDkgNzUsNTIyIDc1LDcwMSAxMjEsODM5IDIxMyw5MzggMzA1LDEwMzcgNDI0LDEwODYgNTcxLDEwODYgNzEzLDEwODYgODI5LDEwMzggOTE5LDk0MSAxMDA5LDg0NCAxMDU0LDcwOCAxMDU0LDUzMyAxMDU0LDUyMiAxMDU0LDUwNiAxMDUzLDQ4NSBMIDI2MSw0ODUgQyAyNjgsMzY4IDMwMSwyNzkgMzYwLDIxNyA0MTksMTU1IDQ5MywxMjQgNTgyLDEyNCA2NDgsMTI0IDcwNCwxNDEgNzUxLDE3NiA3OTgsMjExIDgzNSwyNjYgODYyLDM0MiBaIE0gMjcxLDYzMyBMIDg2NCw2MzMgQyA4NTYsNzIyIDgzMyw3ODkgNzk2LDgzNCA3MzksOTAzIDY2NCw5MzggNTczLDkzOCA0OTAsOTM4IDQyMSw5MTAgMzY1LDg1NSAzMDgsODAwIDI3Nyw3MjYgMjcxLDYzMyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJhIiBob3Jpei1hZHYteD0iOTk0IiBkPSJNIDgyOCwxMzEgQyA3NjEsNzQgNjk3LDM0IDYzNiwxMSA1NzQsLTEyIDUwOCwtMjQgNDM3LC0yNCAzMjAsLTI0IDIzMSw1IDE2OCw2MiAxMDUsMTE5IDc0LDE5MSA3NCwyODAgNzQsMzMyIDg2LDM4MCAxMTAsNDIzIDEzMyw0NjYgMTY0LDUwMCAyMDMsNTI2IDI0MSw1NTIgMjg0LDU3MiAzMzIsNTg1IDM2Nyw1OTQgNDIxLDYwMyA0OTIsNjEyIDYzNyw2MjkgNzQ0LDY1MCA4MTMsNjc0IDgxNCw2OTkgODE0LDcxNCA4MTQsNzIxIDgxNCw3OTQgNzk3LDg0NiA3NjMsODc2IDcxNyw5MTcgNjQ5LDkzNyA1NTgsOTM3IDQ3Myw5MzcgNDExLDkyMiAzNzEsODkzIDMzMCw4NjMgMzAwLDgxMCAyODEsNzM1IEwgMTA1LDc1OSBDIDEyMSw4MzQgMTQ3LDg5NSAxODQsOTQyIDIyMSw5ODggMjc0LDEwMjQgMzQzLDEwNDkgNDEyLDEwNzQgNDkzLDEwODYgNTg0LDEwODYgNjc1LDEwODYgNzQ4LDEwNzUgODA1LDEwNTQgODYyLDEwMzMgOTAzLDEwMDYgOTMwLDk3NCA5NTcsOTQxIDk3NSw5MDAgOTg2LDg1MSA5OTIsODIwIDk5NSw3NjUgOTk1LDY4NSBMIDk5NSw0NDUgQyA5OTUsMjc4IDk5OSwxNzIgMTAwNywxMjggMTAxNCw4MyAxMDI5LDQxIDEwNTIsMCBMIDg2NCwwIEMgODQ1LDM3IDgzMyw4MSA4MjgsMTMxIFogTSA4MTMsNTMzIEMgNzQ4LDUwNiA2NTAsNDg0IDUxOSw0NjUgNDQ1LDQ1NCAzOTMsNDQyIDM2Miw0MjkgMzMxLDQxNiAzMDgsMzk2IDI5MSwzNzEgMjc0LDM0NSAyNjYsMzE2IDI2NiwyODUgMjY2LDIzNyAyODQsMTk3IDMyMSwxNjUgMzU3LDEzMyA0MTAsMTE3IDQ4MCwxMTcgNTQ5LDExNyA2MTEsMTMyIDY2NSwxNjMgNzE5LDE5MyA3NTksMjM0IDc4NCwyODcgODAzLDMyOCA4MTMsMzg4IDgxMyw0NjcgWiIvPgogIDwvZm9udD4KIDwvZGVmcz4KIDxkZWZzPgogIDxmb250IGlkPSJFbWJlZGRlZEZvbnRfMyIgaG9yaXotYWR2LXg9IjIwNDgiPgogICA8Zm9udC1mYWNlIGZvbnQtZmFtaWx5PSJBcmlhbCBlbWJlZGRlZCIgdW5pdHMtcGVyLWVtPSIyMDQ4IiBmb250LXdlaWdodD0iYm9sZCIgZm9udC1zdHlsZT0ibm9ybWFsIiBhc2NlbnQ9IjE4NzAiIGRlc2NlbnQ9IjQzOSIvPgogICA8bWlzc2luZy1nbHlwaCBob3Jpei1hZHYteD0iMjA0OCIgZD0iTSAwLDAgTCAyMDQ3LDAgMjA0NywyMDQ3IDAsMjA0NyAwLDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ieSIgaG9yaXotYWR2LXg9IjExMDkiIGQ9Ik0gMTQsMTA2MiBMIDMxMywxMDYyIDU2NywzMDggODE1LDEwNjIgMTEwNiwxMDYyIDczMSw0MCA2NjQsLTE0NSBDIDYzOSwtMjA3IDYxNiwtMjU0IDU5NCwtMjg3IDU3MSwtMzIwIDU0NiwtMzQ2IDUxNywtMzY3IDQ4OCwtMzg3IDQ1MiwtNDAzIDQxMCwtNDE0IDM2NywtNDI1IDMxOSwtNDMxIDI2NiwtNDMxIDIxMiwtNDMxIDE1OSwtNDI1IDEwNywtNDE0IEwgODIsLTE5NCBDIDEyNiwtMjAzIDE2NiwtMjA3IDIwMSwtMjA3IDI2NiwtMjA3IDMxNSwtMTg4IDM0NiwtMTUwIDM3NywtMTExIDQwMSwtNjIgNDE4LC0zIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InQiIGhvcml6LWFkdi14PSI2NDgiIGQ9Ik0gNjM0LDEwNjIgTCA2MzQsODM4IDQ0Miw4MzggNDQyLDQxMCBDIDQ0MiwzMjMgNDQ0LDI3MyA0NDgsMjU5IDQ1MSwyNDQgNDYwLDIzMiA0NzMsMjIzIDQ4NiwyMTQgNTAxLDIwOSA1MjAsMjA5IDU0NiwyMDkgNTg0LDIxOCA2MzMsMjM2IEwgNjU3LDE4IEMgNTkyLC0xMCA1MTgsLTI0IDQzNSwtMjQgMzg0LC0yNCAzMzksLTE2IDI5OCwyIDI1NywxOSAyMjgsNDEgMjA5LDY4IDE5MCw5NSAxNzYsMTMxIDE2OSwxNzcgMTYzLDIxMCAxNjAsMjc2IDE2MCwzNzUgTCAxNjAsODM4IDMxLDgzOCAzMSwxMDYyIDE2MCwxMDYyIDE2MCwxMjczIDQ0MiwxNDM3IDQ0MiwxMDYyIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InMiIGhvcml6LWFkdi14PSIxMDE3IiBkPSJNIDQ4LDMwMyBMIDMzMCwzNDYgQyAzNDIsMjkxIDM2NiwyNTAgNDAzLDIyMiA0NDAsMTkzIDQ5MSwxNzkgNTU3LDE3OSA2MzAsMTc5IDY4NCwxOTIgNzIxLDIxOSA3NDYsMjM4IDc1OCwyNjMgNzU4LDI5NCA3NTgsMzE1IDc1MSwzMzMgNzM4LDM0NyA3MjQsMzYwIDY5MywzNzMgNjQ0LDM4NCA0MTcsNDM0IDI3NCw0ODAgMjEzLDUyMSAxMjksNTc4IDg3LDY1OCA4Nyw3NjAgODcsODUyIDEyMyw5MjkgMTk2LDk5MiAyNjksMTA1NSAzODEsMTA4NiA1MzQsMTA4NiA2NzksMTA4NiA3ODcsMTA2MiA4NTgsMTAxNSA5MjksOTY4IDk3Nyw4OTggMTAwNCw4MDUgTCA3MzksNzU2IEMgNzI4LDc5NyA3MDYsODI5IDY3NSw4NTEgNjQzLDg3MyA1OTgsODg0IDUzOSw4ODQgNDY1LDg4NCA0MTIsODc0IDM4MCw4NTMgMzU5LDgzOCAzNDgsODE5IDM0OCw3OTYgMzQ4LDc3NiAzNTcsNzU5IDM3Niw3NDUgNDAxLDcyNiA0ODksNzAwIDYzOSw2NjYgNzg4LDYzMiA4OTMsNTkwIDk1Miw1NDEgMTAxMSw0OTEgMTA0MCw0MjEgMTA0MCwzMzIgMTA0MCwyMzUgOTk5LDE1MSA5MTgsODEgODM3LDExIDcxNiwtMjQgNTU3LC0yNCA0MTIsLTI0IDI5OCw1IDIxNCw2NCAxMjksMTIzIDc0LDIwMiA0OCwzMDMgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iciIgaG9yaXotYWR2LXg9IjcxNyIgZD0iTSA0MTYsMCBMIDEzNSwwIDEzNSwxMDYyIDM5NiwxMDYyIDM5Niw5MTEgQyA0NDEsOTgyIDQ4MSwxMDI5IDUxNywxMDUyIDU1MiwxMDc1IDU5MywxMDg2IDYzOCwxMDg2IDcwMiwxMDg2IDc2NCwxMDY4IDgyMywxMDMzIEwgNzM2LDc4OCBDIDY4OSw4MTkgNjQ1LDgzNCA2MDQsODM0IDU2NSw4MzQgNTMxLDgyMyA1MDQsODAyIDQ3Nyw3ODAgNDU1LDc0MSA0NDAsNjg0IDQyNCw2MjcgNDE2LDUwOSA0MTYsMzI4IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InAiIGhvcml6LWFkdi14PSIxMDQwIiBkPSJNIDEzOSwxMDYyIEwgNDAxLDEwNjIgNDAxLDkwNiBDIDQzNSw5NTkgNDgxLDEwMDMgNTM5LDEwMzYgNTk3LDEwNjkgNjYxLDEwODYgNzMyLDEwODYgODU1LDEwODYgOTYwLDEwMzggMTA0Niw5NDEgMTEzMiw4NDQgMTE3NSw3MTAgMTE3NSw1MzcgMTE3NSwzNjAgMTEzMiwyMjIgMTA0NSwxMjQgOTU4LDI1IDg1MywtMjQgNzMwLC0yNCA2NzEsLTI0IDYxOCwtMTIgNTcxLDExIDUyMywzNCA0NzMsNzQgNDIwLDEzMSBMIDQyMCwtNDA0IDEzOSwtNDA0IFogTSA0MTcsNTQ5IEMgNDE3LDQzMCA0NDEsMzQyIDQ4OCwyODUgNTM1LDIyOCA1OTMsMTk5IDY2MSwxOTkgNzI2LDE5OSA3ODEsMjI1IDgyNCwyNzggODY3LDMzMCA4ODksNDE2IDg4OSw1MzUgODg5LDY0NiA4NjcsNzI5IDgyMiw3ODMgNzc3LDgzNyA3MjIsODY0IDY1Niw4NjQgNTg3LDg2NCA1MzAsODM4IDQ4NSw3ODUgNDQwLDczMiA0MTcsNjUzIDQxNyw1NDkgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibyIgaG9yaXotYWR2LXg9IjExMzMiIGQ9Ik0gODIsNTQ2IEMgODIsNjM5IDEwNSw3MzAgMTUxLDgxNyAxOTcsOTA0IDI2Miw5NzEgMzQ3LDEwMTcgNDMxLDEwNjMgNTI1LDEwODYgNjI5LDEwODYgNzkwLDEwODYgOTIxLDEwMzQgMTAyNCw5MzAgMTEyNyw4MjUgMTE3OCw2OTMgMTE3OCw1MzQgMTE3OCwzNzMgMTEyNiwyNDAgMTAyMywxMzUgOTE5LDI5IDc4OCwtMjQgNjMxLC0yNCA1MzQsLTI0IDQ0MSwtMiAzNTMsNDIgMjY0LDg2IDE5NywxNTEgMTUxLDIzNiAxMDUsMzIxIDgyLDQyNCA4Miw1NDYgWiBNIDM3MCw1MzEgQyAzNzAsNDI2IDM5NSwzNDUgNDQ1LDI4OSA0OTUsMjMzIDU1NywyMDUgNjMwLDIwNSA3MDMsMjA1IDc2NSwyMzMgODE1LDI4OSA4NjQsMzQ1IDg4OSw0MjYgODg5LDUzMyA4ODksNjM3IDg2NCw3MTcgODE1LDc3MyA3NjUsODI5IDcwMyw4NTcgNjMwLDg1NyA1NTcsODU3IDQ5NSw4MjkgNDQ1LDc3MyAzOTUsNzE3IDM3MCw2MzYgMzcwLDUzMSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJuIiBob3Jpei1hZHYteD0iOTkzIiBkPSJNIDExMTMsMCBMIDgzMiwwIDgzMiw1NDIgQyA4MzIsNjU3IDgyNiw3MzEgODE0LDc2NSA4MDIsNzk4IDc4Myw4MjQgNzU2LDg0MyA3MjksODYyIDY5Niw4NzEgNjU4LDg3MSA2MDksODcxIDU2Niw4NTggNTI3LDgzMSA0ODgsODA0IDQ2Miw3NjkgNDQ4LDcyNSA0MzMsNjgxIDQyNiw2MDAgNDI2LDQ4MSBMIDQyNiwwIDE0NSwwIDE0NSwxMDYyIDQwNiwxMDYyIDQwNiw5MDYgQyA0OTksMTAyNiA2MTUsMTA4NiA3NTYsMTA4NiA4MTgsMTA4NiA4NzUsMTA3NSA5MjYsMTA1MyA5NzcsMTAzMCAxMDE2LDEwMDIgMTA0Myw5NjcgMTA2OSw5MzIgMTA4Nyw4OTMgMTA5OCw4NDkgMTEwOCw4MDUgMTExMyw3NDIgMTExMyw2NjAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibCIgaG9yaXotYWR2LXg9IjMwMSIgZD0iTSAxNDcsMCBMIDE0NywxNDY2IDQyOCwxNDY2IDQyOCwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImkiIGhvcml6LWFkdi14PSIzMDEiIGQ9Ik0gMTQ3LDEyMDYgTCAxNDcsMTQ2NiA0MjgsMTQ2NiA0MjgsMTIwNiBaIE0gMTQ3LDAgTCAxNDcsMTA2MiA0MjgsMTA2MiA0MjgsMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJnIiBob3Jpei1hZHYteD0iMTA2MyIgZD0iTSAxMjEsLTcwIEwgNDQyLC0xMDkgQyA0NDcsLTE0NiA0NjAsLTE3MiA0NzksLTE4NiA1MDYsLTIwNiA1NDgsLTIxNiA2MDUsLTIxNiA2NzgsLTIxNiA3MzMsLTIwNSA3NzAsLTE4MyA3OTUsLTE2OCA4MTMsLTE0NSA4MjYsLTExMiA4MzUsLTg5IDgzOSwtNDYgODM5LDE3IEwgODM5LDE3MiBDIDc1NSw1NyA2NDksMCA1MjEsMCAzNzgsMCAyNjUsNjAgMTgyLDE4MSAxMTcsMjc2IDg0LDM5NSA4NCw1MzcgODQsNzE1IDEyNyw4NTEgMjEzLDk0NSAyOTgsMTAzOSA0MDUsMTA4NiA1MzIsMTA4NiA2NjMsMTA4NiA3NzIsMTAyOCA4NTcsOTEzIEwgODU3LDEwNjIgMTEyMCwxMDYyIDExMjAsMTA5IEMgMTEyMCwtMTYgMTExMCwtMTEwIDEwODksLTE3MiAxMDY4LC0yMzQgMTAzOSwtMjgzIDEwMDIsLTMxOCA5NjUsLTM1MyA5MTUsLTM4MSA4NTMsLTQwMSA3OTAsLTQyMSA3MTEsLTQzMSA2MTYsLTQzMSA0MzYsLTQzMSAzMDgsLTQwMCAyMzMsLTMzOSAxNTgsLTI3NyAxMjAsLTE5OSAxMjAsLTEwNCAxMjAsLTk1IDEyMCwtODMgMTIxLC03MCBaIE0gMzcyLDU1MyBDIDM3Miw0NDAgMzk0LDM1OCA0MzgsMzA2IDQ4MSwyNTMgNTM1LDIyNyA1OTksMjI3IDY2OCwyMjcgNzI2LDI1NCA3NzMsMzA4IDgyMCwzNjEgODQ0LDQ0MSA4NDQsNTQ2IDg0NCw2NTYgODIxLDczOCA3NzYsNzkxIDczMSw4NDQgNjczLDg3MSA2MDQsODcxIDUzNyw4NzEgNDgxLDg0NSA0MzgsNzkzIDM5NCw3NDAgMzcyLDY2MCAzNzIsNTUzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImYiIGhvcml6LWFkdi14PSI3NDAiIGQ9Ik0gMjQsMTA2MiBMIDE4MCwxMDYyIDE4MCwxMTQyIEMgMTgwLDEyMzEgMTkwLDEyOTggMjA5LDEzNDIgMjI4LDEzODYgMjYzLDE0MjIgMzE0LDE0NTAgMzY1LDE0NzcgNDI5LDE0OTEgNTA3LDE0OTEgNTg3LDE0OTEgNjY1LDE0NzkgNzQyLDE0NTUgTCA3MDQsMTI1OSBDIDY1OSwxMjcwIDYxNiwxMjc1IDU3NSwxMjc1IDUzNCwxMjc1IDUwNSwxMjY2IDQ4OCwxMjQ3IDQ3MCwxMjI4IDQ2MSwxMTkxIDQ2MSwxMTM3IEwgNDYxLDEwNjIgNjcxLDEwNjIgNjcxLDg0MSA0NjEsODQxIDQ2MSwwIDE4MCwwIDE4MCw4NDEgMjQsODQxIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImUiIGhvcml6LWFkdi14PSIxMDQwIiBkPSJNIDc2MiwzMzggTCAxMDQyLDI5MSBDIDEwMDYsMTg4IDk0OSwxMTAgODcyLDU3IDc5NCwzIDY5NywtMjQgNTgwLC0yNCAzOTUsLTI0IDI1OSwzNiAxNzAsMTU3IDEwMCwyNTQgNjUsMzc2IDY1LDUyMyA2NSw2OTkgMTExLDgzNyAyMDMsOTM3IDI5NSwxMDM2IDQxMSwxMDg2IDU1MiwxMDg2IDcxMCwxMDg2IDgzNSwxMDM0IDkyNiw5MzAgMTAxNyw4MjUgMTA2MSw2NjUgMTA1Nyw0NTAgTCAzNTMsNDUwIEMgMzU1LDM2NyAzNzgsMzAyIDQyMSwyNTYgNDY0LDIwOSA1MTgsMTg2IDU4MywxODYgNjI3LDE4NiA2NjQsMTk4IDY5NCwyMjIgNzI0LDI0NiA3NDcsMjg1IDc2MiwzMzggWiBNIDc3OCw2MjIgQyA3NzYsNzAzIDc1NSw3NjUgNzE1LDgwOCA2NzUsODUwIDYyNiw4NzEgNTY5LDg3MSA1MDgsODcxIDQ1Nyw4NDkgNDE3LDgwNCAzNzcsNzU5IDM1Nyw2OTkgMzU4LDYyMiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJjIiBob3Jpei1hZHYteD0iMTA0MCIgZD0iTSAxMDczLDc0OCBMIDc5Niw2OTggQyA3ODcsNzUzIDc2Niw3OTUgNzMzLDgyMyA3MDAsODUxIDY1Nyw4NjUgNjA0LDg2NSA1MzQsODY1IDQ3OCw4NDEgNDM3LDc5MyAzOTUsNzQ0IDM3NCw2NjMgMzc0LDU1MCAzNzQsNDI0IDM5NSwzMzUgNDM4LDI4MyA0ODAsMjMxIDUzNywyMDUgNjA4LDIwNSA2NjEsMjA1IDcwNSwyMjAgNzM5LDI1MSA3NzMsMjgxIDc5NywzMzMgODExLDQwNyBMIDEwODcsMzYwIEMgMTA1OCwyMzMgMTAwMywxMzggOTIyLDczIDg0MSw4IDczMiwtMjQgNTk1LC0yNCA0NDAsLTI0IDMxNiwyNSAyMjQsMTIzIDEzMSwyMjEgODUsMzU3IDg1LDUzMCA4NSw3MDUgMTMxLDg0MiAyMjQsOTQwIDMxNywxMDM3IDQ0MiwxMDg2IDYwMCwxMDg2IDcyOSwxMDg2IDgzMiwxMDU4IDkwOSwxMDAzIDk4NSw5NDcgMTA0MCw4NjIgMTA3Myw3NDggWiIvPgogICA8Z2x5cGggdW5pY29kZT0iYiIgaG9yaXotYWR2LXg9IjEwNjQiIGQ9Ik0gMTM1LDAgTCAxMzUsMTQ2NiA0MTYsMTQ2NiA0MTYsOTM4IEMgNTAzLDEwMzcgNjA1LDEwODYgNzI0LDEwODYgODUzLDEwODYgOTYwLDEwMzkgMTA0NSw5NDYgMTEzMCw4NTIgMTE3Miw3MTcgMTE3Miw1NDIgMTE3MiwzNjEgMTEyOSwyMjEgMTA0MywxMjMgOTU2LDI1IDg1MSwtMjQgNzI4LC0yNCA2NjcsLTI0IDYwOCwtOSA1NDksMjIgNDkwLDUyIDQzOSw5NyAzOTYsMTU2IEwgMzk2LDAgWiBNIDQxNCw1NTQgQyA0MTQsNDQ0IDQzMSwzNjMgNDY2LDMxMCA1MTUsMjM1IDU3OSwxOTggNjYwLDE5OCA3MjIsMTk4IDc3NSwyMjUgODE5LDI3OCA4NjIsMzMxIDg4NCw0MTQgODg0LDUyOCA4ODQsNjQ5IDg2Miw3MzcgODE4LDc5MSA3NzQsODQ0IDcxOCw4NzEgNjQ5LDg3MSA1ODIsODcxIDUyNiw4NDUgNDgxLDc5MyA0MzYsNzQwIDQxNCw2NjEgNDE0LDU1NCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJhIiBob3Jpei1hZHYteD0iMTAxNyIgZD0iTSAzNTcsNzM4IEwgMTAyLDc4NCBDIDEzMSw4ODcgMTgwLDk2MyAyNTAsMTAxMiAzMjAsMTA2MSA0MjQsMTA4NiA1NjIsMTA4NiA2ODcsMTA4NiA3ODEsMTA3MSA4NDIsMTA0MiA5MDMsMTAxMiA5NDcsOTc0IDk3Miw5MjkgOTk3LDg4MyAxMDA5LDc5OSAxMDA5LDY3NyBMIDEwMDYsMzQ5IEMgMTAwNiwyNTYgMTAxMSwxODcgMTAyMCwxNDMgMTAyOSw5OCAxMDQ1LDUxIDEwNzAsMCBMIDc5MiwwIEMgNzg1LDE5IDc3Niw0NiA3NjUsODMgNzYwLDEwMCA3NTcsMTExIDc1NSwxMTYgNzA3LDY5IDY1NiwzNCA2MDEsMTEgNTQ2LC0xMiA0ODgsLTI0IDQyNiwtMjQgMzE3LC0yNCAyMzEsNiAxNjgsNjUgMTA1LDEyNCA3MywxOTkgNzMsMjkwIDczLDM1MCA4Nyw0MDQgMTE2LDQ1MSAxNDUsNDk4IDE4NSw1MzQgMjM3LDU1OSAyODgsNTg0IDM2Myw2MDUgNDYwLDYyNCA1OTEsNjQ5IDY4Miw2NzIgNzMzLDY5MyBMIDczMyw3MjEgQyA3MzMsNzc1IDcyMCw4MTQgNjkzLDgzNyA2NjYsODYwIDYxNiw4NzEgNTQyLDg3MSA0OTIsODcxIDQ1Myw4NjEgNDI1LDg0MiAzOTcsODIyIDM3NCw3ODcgMzU3LDczOCBaIE0gNzMzLDUxMCBDIDY5Nyw0OTggNjQwLDQ4NCA1NjIsNDY3IDQ4NCw0NTAgNDMzLDQzNCA0MDksNDE4IDM3MiwzOTIgMzU0LDM1OSAzNTQsMzE5IDM1NCwyODAgMzY5LDI0NiAzOTgsMjE3IDQyNywxODggNDY1LDE3NCA1MTAsMTc0IDU2MSwxNzQgNjA5LDE5MSA2NTUsMjI0IDY4OSwyNDkgNzExLDI4MCA3MjIsMzE3IDcyOSwzNDEgNzMzLDM4NyA3MzMsNDU0IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IlUiIGhvcml6LWFkdi14PSIxMTc4IiBkPSJNIDE0NywxNDY2IEwgNDQzLDE0NjYgNDQzLDY3MiBDIDQ0Myw1NDYgNDQ3LDQ2NCA0NTQsNDI3IDQ2NywzNjcgNDk3LDMxOSA1NDUsMjgzIDU5MiwyNDYgNjU3LDIyOCA3NDAsMjI4IDgyNCwyMjggODg3LDI0NSA5MzAsMjgwIDk3MywzMTQgOTk4LDM1NiAxMDA3LDQwNiAxMDE2LDQ1NiAxMDIwLDUzOSAxMDIwLDY1NSBMIDEwMjAsMTQ2NiAxMzE2LDE0NjYgMTMxNiw2OTYgQyAxMzE2LDUyMCAxMzA4LDM5NiAxMjkyLDMyMyAxMjc2LDI1MCAxMjQ3LDE4OSAxMjA0LDEzOSAxMTYxLDg5IDExMDMsNDkgMTAzMSwyMCA5NTksLTEwIDg2NSwtMjUgNzQ5LC0yNSA2MDksLTI1IDUwMywtOSA0MzEsMjQgMzU4LDU2IDMwMSw5OCAyNTksMTUwIDIxNywyMDEgMTg5LDI1NSAxNzYsMzEyIDE1NywzOTYgMTQ3LDUyMCAxNDcsNjg0IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IlMiIGhvcml6LWFkdi14PSIxMjAyIiBkPSJNIDc0LDQ3NyBMIDM2Miw1MDUgQyAzNzksNDA4IDQxNSwzMzcgNDY4LDI5MiA1MjEsMjQ3IDU5MiwyMjQgNjgyLDIyNCA3NzcsMjI0IDg0OSwyNDQgODk4LDI4NSA5NDYsMzI1IDk3MCwzNzIgOTcwLDQyNiA5NzAsNDYxIDk2MCw0OTAgOTQwLDUxNSA5MTksNTM5IDg4NCw1NjAgODMzLDU3OCA3OTgsNTkwIDcxOSw2MTEgNTk2LDY0MiA0MzcsNjgxIDMyNiw3MzAgMjYyLDc4NyAxNzIsODY4IDEyNyw5NjYgMTI3LDEwODIgMTI3LDExNTcgMTQ4LDEyMjcgMTkxLDEyOTIgMjMzLDEzNTcgMjk0LDE0MDYgMzc0LDE0NDAgNDUzLDE0NzQgNTQ5LDE0OTEgNjYyLDE0OTEgODQ2LDE0OTEgOTg1LDE0NTEgMTA3OCwxMzcwIDExNzEsMTI4OSAxMjE5LDExODIgMTIyNCwxMDQ3IEwgOTI4LDEwMzQgQyA5MTUsMTEwOSA4ODgsMTE2NCA4NDcsMTE5NyA4MDUsMTIzMCA3NDIsMTI0NiA2NTksMTI0NiA1NzMsMTI0NiA1MDYsMTIyOCA0NTcsMTE5MyA0MjYsMTE3MCA0MTAsMTE0MCA0MTAsMTEwMiA0MTAsMTA2NyA0MjUsMTAzOCA0NTQsMTAxMyA0OTEsOTgyIDU4Miw5NDkgNzI2LDkxNSA4NzAsODgxIDk3Nyw4NDYgMTA0Niw4MTAgMTExNSw3NzMgMTE2OSw3MjQgMTIwOCw2NjEgMTI0Nyw1OTggMTI2Niw1MjAgMTI2Niw0MjcgMTI2NiwzNDMgMTI0MywyNjQgMTE5NiwxOTEgMTE0OSwxMTggMTA4Myw2MyA5OTgsMjggOTEzLC04IDgwNiwtMjYgNjc5LC0yNiA0OTQsLTI2IDM1MSwxNyAyNTIsMTAzIDE1MywxODggOTMsMzEzIDc0LDQ3NyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJPIiBob3Jpei1hZHYteD0iMTQ1NiIgZD0iTSA4OSw3MjQgQyA4OSw4NzMgMTExLDk5OSAxNTYsMTEwMCAxODksMTE3NSAyMzUsMTI0MiAyOTMsMTMwMSAzNTAsMTM2MCA0MTMsMTQwNCA0ODIsMTQzMyA1NzMsMTQ3MiA2NzksMTQ5MSA3OTgsMTQ5MSAxMDE0LDE0OTEgMTE4NywxNDI0IDEzMTcsMTI5MCAxNDQ2LDExNTYgMTUxMSw5NzAgMTUxMSw3MzEgMTUxMSw0OTQgMTQ0NywzMDkgMTMxOCwxNzYgMTE4OSw0MiAxMDE3LC0yNSA4MDIsLTI1IDU4NCwtMjUgNDExLDQyIDI4MiwxNzUgMTUzLDMwOCA4OSw0OTEgODksNzI0IFogTSAzOTQsNzM0IEMgMzk0LDU2OCA0MzIsNDQyIDUwOSwzNTcgNTg2LDI3MSA2ODMsMjI4IDgwMSwyMjggOTE5LDIyOCAxMDE2LDI3MSAxMDkyLDM1NiAxMTY3LDQ0MSAxMjA1LDU2OCAxMjA1LDczOCAxMjA1LDkwNiAxMTY4LDEwMzEgMTA5NSwxMTE0IDEwMjEsMTE5NyA5MjMsMTIzOCA4MDEsMTIzOCA2NzksMTIzOCA1ODEsMTE5NiA1MDYsMTExMyA0MzEsMTAyOSAzOTQsOTAzIDM5NCw3MzQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iTSIgaG9yaXotYWR2LXg9IjE0MzIiIGQ9Ik0gMTQ1LDAgTCAxNDUsMTQ2NiA1ODgsMTQ2NiA4NTQsNDY2IDExMTcsMTQ2NiAxNTYxLDE0NjYgMTU2MSwwIDEyODYsMCAxMjg2LDExNTQgOTk1LDAgNzEwLDAgNDIwLDExNTQgNDIwLDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iTCIgaG9yaXotYWR2LXg9IjEwNjMiIGQ9Ik0gMTU3LDAgTCAxNTcsMTQ1NCA0NTMsMTQ1NCA0NTMsMjQ3IDExODksMjQ3IDExODksMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJDIiBob3Jpei1hZHYteD0iMTI5NCIgZD0iTSAxMDg3LDUzOSBMIDEzNzQsNDQ4IEMgMTMzMCwyODggMTI1NywxNjkgMTE1NSw5MiAxMDUyLDE0IDkyMiwtMjUgNzY1LC0yNSA1NzAsLTI1IDQxMCw0MiAyODUsMTc1IDE2MCwzMDggOTcsNDg5IDk3LDcyMCA5Nyw5NjQgMTYwLDExNTQgMjg2LDEyODkgNDEyLDE0MjQgNTc4LDE0OTEgNzgzLDE0OTEgOTYyLDE0OTEgMTEwOCwxNDM4IDEyMjAsMTMzMiAxMjg3LDEyNjkgMTMzNywxMTc5IDEzNzAsMTA2MiBMIDEwNzcsOTkyIEMgMTA2MCwxMDY4IDEwMjQsMTEyOCA5NjksMTE3MiA5MTQsMTIxNiA4NDcsMTIzOCA3NjgsMTIzOCA2NTksMTIzOCA1NzEsMTE5OSA1MDQsMTEyMSA0MzYsMTA0MyA0MDIsOTE3IDQwMiw3NDIgNDAyLDU1NyA0MzUsNDI1IDUwMiwzNDYgNTY5LDI2NyA2NTUsMjI4IDc2MiwyMjggODQxLDIyOCA5MDgsMjUzIDk2NSwzMDMgMTAyMiwzNTMgMTA2Miw0MzIgMTA4Nyw1MzkgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iQCIgaG9yaXotYWR2LXg9IjE5NjQiIGQ9Ik0gMTc3NywxOSBMIDE5OTAsMTkgQyAxOTIzLC0xMTYgMTgyMSwtMjIyIDE2ODMsLTI5OSAxNTI1LC0zODcgMTMzMiwtNDMxIDExMDUsLTQzMSA4ODUsLTQzMSA2OTUsLTM5NCA1MzUsLTMyMCAzNzUsLTI0NSAyNTYsLTEzNiAxNzgsMTAgMTAwLDE1NSA2MSwzMTIgNjEsNDgzIDYxLDY3MCAxMDUsODQ1IDE5NCwxMDA3IDI4MywxMTY4IDQwNCwxMjkwIDU1OCwxMzcxIDcxMiwxNDUyIDg4OCwxNDkyIDEwODYsMTQ5MiAxMjU0LDE0OTIgMTQwMywxNDU5IDE1MzQsMTM5NCAxNjY1LDEzMjkgMTc2NSwxMjM2IDE4MzQsMTExNiAxOTAzLDk5NSAxOTM3LDg2NCAxOTM3LDcyMSAxOTM3LDU1MSAxODg1LDM5NyAxNzgwLDI2MCAxNjQ5LDg3IDE0ODAsMCAxMjc1LDAgMTIyMCwwIDExNzgsMTAgMTE1MCwyOSAxMTIyLDQ4IDExMDMsNzcgMTA5NCwxMTQgMTAxNSwzOCA5MjUsMCA4MjIsMCA3MTEsMCA2MjAsMzggNTQ3LDExNSA0NzQsMTkxIDQzNywyOTIgNDM3LDQxOSA0MzcsNTc2IDQ4MSw3MTkgNTY5LDg0OCA2NzYsMTAwNSA4MTIsMTA4NCA5NzksMTA4NCAxMDk4LDEwODQgMTE4NSwxMDM5IDEyNDIsOTQ4IEwgMTI2NywxMDU5IDE1MzEsMTA1OSAxMzgwLDM0MiBDIDEzNzEsMjk3IDEzNjYsMjY3IDEzNjYsMjU0IDEzNjYsMjM3IDEzNzAsMjI1IDEzNzgsMjE3IDEzODUsMjA4IDEzOTQsMjA0IDE0MDUsMjA0IDE0MzcsMjA0IDE0NzgsMjIzIDE1MjksMjYyIDE1OTcsMzEzIDE2NTIsMzgxIDE2OTQsNDY2IDE3MzYsNTUxIDE3NTcsNjQwIDE3NTcsNzMxIDE3NTcsODk1IDE2OTgsMTAzMiAxNTc5LDExNDMgMTQ2MCwxMjUzIDEyOTUsMTMwOCAxMDgyLDEzMDggOTAxLDEzMDggNzQ4LDEyNzEgNjIzLDExOTggNDk3LDExMjQgNDAyLDEwMjAgMzM5LDg4NyAyNzUsNzUzIDI0Myw2MTQgMjQzLDQ2OSAyNDMsMzI4IDI3OSwyMDAgMzUwLDg1IDQyMSwtMzAgNTIwLC0xMTUgNjQ5LC0xNjggNzc3LC0yMjEgOTI0LC0yNDcgMTA4OSwtMjQ3IDEyNDgsLTI0NyAxMzg1LC0yMjUgMTUwMCwtMTgxIDE2MTUsLTEzNiAxNzA3LC03MCAxNzc3LDE5IFogTSA2OTYsNDMwIEMgNjk2LDM0NSA3MTMsMjgzIDc0OCwyNDMgNzgyLDIwMyA4MjQsMTgzIDg3NSwxODMgOTEzLDE4MyA5NDksMTkyIDk4MiwyMTEgMTAwNywyMjQgMTAzMiwyNDUgMTA1NywyNzQgMTA5MiwzMTUgMTEyMywzNzQgMTE0OSw0NTIgMTE3NCw1MzAgMTE4Nyw2MDMgMTE4Nyw2NzAgMTE4Nyw3NDUgMTE3MCw4MDMgMTEzNSw4NDQgMTEwMCw4ODQgMTA1NSw5MDQgMTAwMiw5MDQgOTQ1LDkwNCA4OTIsODgyIDg0Myw4MzggNzk0LDc5MyA3NTgsNzMwIDczMyw2NDggNzA4LDU2NiA2OTYsNDkzIDY5Niw0MzAgWiIvPgogIDwvZm9udD4KIDwvZGVmcz4KIDxkZWZzIGNsYXNzPSJUZXh0U2hhcGVJbmRleCI+CiAgPGcgb29vOnNsaWRlPSJpZDEiIG9vbzppZC1saXN0PSJpZDMgaWQ0IGlkNSBpZDYgaWQ3IGlkOCBpZDkgaWQxMCBpZDExIGlkMTIgaWQxMyBpZDE0IGlkMTUgaWQxNiBpZDE3Ii8+CiA8L2RlZnM+CiA8ZGVmcyBjbGFzcz0iRW1iZWRkZWRCdWxsZXRDaGFycyI+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTU3MzU2IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSA1ODAsMTE0MSBMIDExNjMsNTcxIDU4MCwwIC00LDU3MSA1ODAsMTE0MSBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS01NzM1NCIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gOCwxMTI4IEwgMTEzNywxMTI4IDExMzcsMCA4LDAgOCwxMTI4IFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTEwMTQ2IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAxNzQsMCBMIDYwMiw3MzkgMTc0LDE0ODEgMTQ1Niw3MzkgMTc0LDAgWiBNIDEzNTgsNzM5IEwgMzA5LDEzNDYgNjU5LDczOSAxMzU4LDczOSBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS0xMDEzMiIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gMjAxNSw3MzkgTCAxMjc2LDAgNzE3LDAgMTI2MCw1NDMgMTc0LDU0MyAxNzQsOTM2IDEyNjAsOTM2IDcxNywxNDgxIDEyNzQsMTQ4MSAyMDE1LDczOSBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS0xMDAwNyIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gMCwtMiBDIC03LDE0IC0xNiwyNyAtMjUsMzcgTCAzNTYsNTY3IEMgMjYyLDgyMyAyMTUsOTUyIDIxNSw5NTQgMjE1LDk3OSAyMjgsOTkyIDI1NSw5OTIgMjY0LDk5MiAyNzYsOTkwIDI4OSw5ODcgMzEwLDk5MSAzMzEsOTk5IDM1NCwxMDEyIEwgMzgxLDk5OSA0OTIsNzQ4IDc3MiwxMDQ5IDgzNiwxMDI0IDg2MCwxMDQ5IEMgODgxLDEwMzkgOTAxLDEwMjUgOTIyLDEwMDYgODg2LDkzNyA4MzUsODYzIDc3MCw3ODQgNzY5LDc4MyA3MTAsNzE2IDU5NCw1ODQgTCA3NzQsMjIzIEMgNzc0LDE5NiA3NTMsMTY4IDcxMSwxMzkgTCA3MjcsMTE5IEMgNzE3LDkwIDY5OSw3NiA2NzIsNzYgNjQxLDc2IDU3MCwxNzggNDU3LDM4MSBMIDE2NCwtNzYgQyAxNDIsLTExMCAxMTEsLTEyNyA3MiwtMTI3IDMwLC0xMjcgOSwtMTEwIDgsLTc2IDEsLTY3IC0yLC01MiAtMiwtMzIgLTIsLTIzIC0xLC0xMyAwLC0yIFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTEwMDA0IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAyODUsLTMzIEMgMTgyLC0zMyAxMTEsMzAgNzQsMTU2IDUyLDIyOCA0MSwzMzMgNDEsNDcxIDQxLDU0OSA1NSw2MTYgODIsNjcyIDExNiw3NDMgMTY5LDc3OCAyNDAsNzc4IDI5Myw3NzggMzI4LDc0NyAzNDYsNjg0IEwgMzY5LDUwOCBDIDM3Nyw0NDQgMzk3LDQxMSA0MjgsNDEwIEwgMTE2MywxMTE2IEMgMTE3NCwxMTI3IDExOTYsMTEzMyAxMjI5LDExMzMgMTI3MSwxMTMzIDEyOTIsMTExOCAxMjkyLDEwODcgTCAxMjkyLDk2NSBDIDEyOTIsOTI5IDEyODIsOTAxIDEyNjIsODgxIEwgNDQyLDQ3IEMgMzkwLC02IDMzOCwtMzMgMjg1LC0zMyBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS05Njc5IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSA4MTMsMCBDIDYzMiwwIDQ4OSw1NCAzODMsMTYxIDI3NiwyNjggMjIzLDQxMSAyMjMsNTkyIDIyMyw3NzMgMjc2LDkxNiAzODMsMTAyMyA0ODksMTEzMCA2MzIsMTE4NCA4MTMsMTE4NCA5OTIsMTE4NCAxMTM2LDExMzAgMTI0NSwxMDIzIDEzNTMsOTE2IDE0MDcsNzcyIDE0MDcsNTkyIDE0MDcsNDEyIDEzNTMsMjY4IDEyNDUsMTYxIDExMzYsNTQgOTkyLDAgODEzLDAgWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtODIyNiIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gMzQ2LDQ1NyBDIDI3Myw0NTcgMjA5LDQ4MyAxNTUsNTM1IDEwMSw1ODYgNzQsNjQ5IDc0LDcyMyA3NCw3OTYgMTAxLDg1OSAxNTUsOTExIDIwOSw5NjMgMjczLDk4OSAzNDYsOTg5IDQxOSw5ODkgNDgwLDk2MyA1MzEsOTEwIDU4Miw4NTkgNjA4LDc5NiA2MDgsNzIzIDYwOCw2NDggNTgzLDU4NiA1MzIsNTM1IDQ4Miw0ODMgNDIwLDQ1NyAzNDYsNDU3IFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTgyMTEiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIC00LDQ1OSBMIDExMzUsNDU5IDExMzUsNjA2IC00LDYwNiAtNCw0NTkgWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtNjE1NDgiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDE3Myw3NDAgQyAxNzMsOTAzIDIzMSwxMDQzIDM0NiwxMTU5IDQ2MiwxMjc0IDYwMSwxMzMyIDc2NSwxMzMyIDkyOCwxMzMyIDEwNjcsMTI3NCAxMTgzLDExNTkgMTI5OSwxMDQzIDEzNTcsOTAzIDEzNTcsNzQwIDEzNTcsNTc3IDEyOTksNDM3IDExODMsMzIyIDEwNjcsMjA2IDkyOCwxNDggNzY1LDE0OCA2MDEsMTQ4IDQ2MiwyMDYgMzQ2LDMyMiAyMzEsNDM3IDE3Myw1NzcgMTczLDc0MCBaIi8+CiAgPC9nPgogPC9kZWZzPgogPGRlZnMgY2xhc3M9IlRleHRFbWJlZGRlZEJpdG1hcHMiLz4KIDxnIGNsYXNzPSJTbGlkZUdyb3VwIj4KICA8Zz4KICAgPGcgaWQ9ImNvbnRhaW5lci1pZDEiPgogICAgPGcgaWQ9ImlkMSIgY2xhc3M9IlNsaWRlIiBjbGlwLXBhdGg9InVybCgjcHJlc2VudGF0aW9uX2NsaXBfcGF0aCkiPgogICAgIDxnIGNsYXNzPSJQYWdlIj4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDMiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxNDk1MCIgeT0iMTM4MDAiIHdpZHRoPSIzMTAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjU1LDI1NSwxNTMpIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMTY1MDAsMTQ4MDAgTCAxNDk1MCwxNDgwMCAxNDk1MCwxMzgwMCAxODA1MCwxMzgwMCAxODA1MCwxNDgwMCAxNjUwMCwxNDgwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNTM5MyIgeT0iMTQyMTMiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPsKr4oCvZW51bWVyYXRpb27igK/CuzwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjM1M3B4IiBmb250LXdlaWdodD0iNzAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTU1OTIiIHk9IjE0NTk5Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5TdGVyZW90eXBlPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQ0Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iODEwMCIgeT0iMTM4MDAiIHdpZHRoPSIzNTAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjU1LDI1NSwxNTMpIiBzdHJva2U9Im5vbmUiIGQ9Ik0gOTg1MCwxNDgwMCBMIDgxMDAsMTQ4MDAgODEwMCwxMzgwMCAxMTYwMCwxMzgwMCAxMTYwMCwxNDgwMCA5ODUwLDE0ODAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijg3NDMiIHk9IjE0MjEzIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj7Cq+KAr2VudW1lcmF0aW9u4oCvwrs8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzNTNweCIgZm9udC13ZWlnaHQ9IjcwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijg3NTYiIHk9IjE0NTk5Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5TcGVjaWZpY2F0aW9uPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5DdXN0b21TaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQ1Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMzgwMCIgeT0iMTM4MDAiIHdpZHRoPSIzNTAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjU1LDI1NSwxNTMpIiBzdHJva2U9Im5vbmUiIGQ9Ik0gNTU1MCwxNDgwMCBMIDM4MDAsMTQ4MDAgMzgwMCwxMzgwMCA3MzAwLDEzODAwIDczMDAsMTQ4MDAgNTU1MCwxNDgwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0NDQzIiB5PSIxNDIxMyI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+wqvigK9lbnVtZXJhdGlvbuKAr8K7PC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzUzcHgiIGZvbnQtd2VpZ2h0PSI3MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0Njc4IiB5PSIxNDU5OSI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+T2JsaWdhdGlvbjwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkNiI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjE0MDAwIiB5PSIxMDEwMCIgd2lkdGg9IjUwMDEiIGhlaWdodD0iMTAwMSIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigyNTUsMjU1LDE1MykiIHN0cm9rZT0ibm9uZSIgZD0iTSAxNjUwMCwxMTEwMCBMIDE0MDAwLDExMTAwIDE0MDAwLDEwMTAwIDE5MDAwLDEwMTAwIDE5MDAwLDExMTAwIDE2NTAwLDExMTAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjE1NTMzIiB5PSIxMDUxMyI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+wqvigK9hbm5vdGF0aW9u4oCvwrs8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzNTNweCIgZm9udC13ZWlnaHQ9IjcwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjE1NTM5IiB5PSIxMDg5OSI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+QENsYXNzaWZpZXI8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDciPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIzODAwIiB5PSIxMDEwMCIgd2lkdGg9Ijc4MDEiIGhlaWdodD0iMTAwMSIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigyNTUsMjU1LDE1MykiIHN0cm9rZT0ibm9uZSIgZD0iTSA3NzAwLDExMTAwIEwgMzgwMCwxMTEwMCAzODAwLDEwMTAwIDExNjAwLDEwMTAwIDExNjAwLDExMTAwIDc3MDAsMTExMDAgWiIvPgogICAgICAgIDx0ZXh0IGNsYXNzPSJUZXh0U2hhcGUiPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iNjczMyIgeT0iMTA1MTMiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPsKr4oCvYW5ub3RhdGlvbuKAr8K7PC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzUzcHgiIGZvbnQtd2VpZ2h0PSI3MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI3MTQ4IiB5PSIxMDg5OSI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+QFVNTDwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkOCI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjM3ODUiIHk9IjEwMDg1IiB3aWR0aD0iNzgzMSIgaGVpZ2h0PSIyOTMxIi8+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJyZ2IoMCwwLDApIiBzdHJva2Utd2lkdGg9IjMwIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDc3MDAsMTMwMDAgTCAzODAwLDEzMDAwIDM4MDAsMTAxMDAgMTE2MDAsMTAxMDAgMTE2MDAsMTMwMDAgNzcwMCwxMzAwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0MzAwIiB5PSIxMTY4OCI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyBpZGVudGlmaWVywqAgICA6IENoYXJhY3RlclN0cmluZzwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iNDMwMCIgeT0iMTIwNDQiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgb2JsaWdhdGlvbiAgICA6IE9ibGlnYXRpb248L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjQzMDAiIHk9IjEyNDAwIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIHNwZWNpZmljYXRpb24gOiBTcGVjaWZpY2F0aW9uPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0MzAwIiB5PSIxMjc1NiI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyB2ZXJzaW9uICAgICAgIDogSW50ZWdlcjwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuTGluZVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDkiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIzNzk5IiB5PSIxMTA5OSIgd2lkdGg9Ijc4MDMiIGhlaWdodD0iMyIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSAzODAwLDExMTAwIEwgMTE2MDAsMTExMDAiLz4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTAiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxMzk4NSIgeT0iMTAwODUiIHdpZHRoPSI1MDMxIiBoZWlnaHQ9IjE5MzEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIHN0cm9rZS13aWR0aD0iMzAiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTY1MDAsMTIwMDAgTCAxNDAwMCwxMjAwMCAxNDAwMCwxMDEwMCAxOTAwMCwxMDEwMCAxOTAwMCwxMjAwMCAxNjUwMCwxMjAwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNDUwMCIgeT0iMTE2ODgiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgdmFsdWXCoDogU3RlcmVvdHlwZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuTGluZVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDExIj4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMTM5OTkiIHk9IjExMDk5IiB3aWR0aD0iNTAwMyIgaGVpZ2h0PSIzIi8+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJyZ2IoMCwwLDApIiBkPSJNIDE0MDAwLDExMTAwIEwgMTkwMDAsMTExMDAiLz4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTIiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxNDkzNSIgeT0iMTM3ODUiIHdpZHRoPSIzMTMxIiBoZWlnaHQ9IjM0MzEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIHN0cm9rZS13aWR0aD0iMzAiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTY1MDAsMTcyMDAgTCAxNDk1MCwxNzIwMCAxNDk1MCwxMzgwMCAxODA1MCwxMzgwMCAxODA1MCwxNzIwMCAxNjUwMCwxNzIwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNTQ1MCIgeT0iMTUzODgiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgdHlwZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTU0NTAiIHk9IjE1NzQ0Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIGRhdGF0eXBlPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNTQ1MCIgeT0iMTYxMDAiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgYWJzdHJhY3Q8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjE1NDUwIiB5PSIxNjQ1NiI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyB1bmlvbjwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTU0NTAiIHk9IjE2ODEyIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIG1ldGFjbGFzczwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuTGluZVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDEzIj4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMTQ5NDkiIHk9IjE0Nzk5IiB3aWR0aD0iMzEwMyIgaGVpZ2h0PSIzIi8+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJyZ2IoMCwwLDApIiBkPSJNIDE0OTUwLDE0ODAwIEwgMTgwNTAsMTQ4MDAiLz4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTQiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIzNzg1IiB5PSIxMzc4NSIgd2lkdGg9IjM1MzEiIGhlaWdodD0iMzAzMSIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgc3Ryb2tlLXdpZHRoPSIzMCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSA1NTUwLDE2ODAwIEwgMzgwMCwxNjgwMCAzODAwLDEzODAwIDczMDAsMTM4MDAgNzMwMCwxNjgwMCA1NTUwLDE2ODAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjQzMDAiIHk9IjE1Mzg4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIG1hbmRhdG9yeTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iNDMwMCIgeT0iMTU3NDQiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgb3B0aW9uYWw8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjQzMDAiIHk9IjE2MTAwIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIGNvbmRpdGlvbmFsPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI0MzAwIiB5PSIxNjQ1NiI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyBmb3JiaWRkZW48L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkxpbmVTaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxNSI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjM3OTkiIHk9IjE0Nzk5IiB3aWR0aD0iMzUwMyIgaGVpZ2h0PSIzIi8+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJyZ2IoMCwwLDApIiBkPSJNIDM4MDAsMTQ4MDAgTCA3MzAwLDE0ODAwIi8+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDE2Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iODA4NSIgeT0iMTM3ODUiIHdpZHRoPSIzNTMxIiBoZWlnaHQ9IjYxMzEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIHN0cm9rZS13aWR0aD0iMzAiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gOTg1MCwxOTkwMCBMIDgxMDAsMTk5MDAgODEwMCwxMzgwMCAxMTYwMCwxMzgwMCAxMTYwMCwxOTkwMCA5ODUwLDE5OTAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijg2MDAiIHk9IjE1Mzg4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIElTT18xOTEwMzwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iODYwMCIgeT0iMTU3NDQiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgSVNPXzE5MTA3PC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI4NjAwIiB5PSIxNjEwMCI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyBJU09fMTkxMDg8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijg2MDAiIHk9IjE2NDU2Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIElTT18xOTEwOTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iODYwMCIgeT0iMTY4MTIiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgSVNPXzE5MTExPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI4NjAwIiB5PSIxNzE2OCI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyBJU09fMTkxMTI8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijg2MDAiIHk9IjE3NTI0Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIElTT18xOTExNTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iODYwMCIgeT0iMTc4ODAiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgSVNPXzE5MTE1XzI8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijg2MDAiIHk9IjE4MjM2Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIElTT18xOTExNV8zPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI4NjAwIiB5PSIxODU5MiI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyBJU09fMTkxNTc8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijg2MDAiIHk9IjE4OTQ4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIElTT18xOTE2MjwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iODYwMCIgeT0iMTkzMDQiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgT0dDXzAxMDA5PC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI4NjAwIiB5PSIxOTY2MCI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyBPR0NfMTQwODM8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkxpbmVTaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxNyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjgwOTkiIHk9IjE0Nzk5IiB3aWR0aD0iMzUwMyIgaGVpZ2h0PSIzIi8+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJyZ2IoMCwwLDApIiBkPSJNIDgxMDAsMTQ4MDAgTCAxMTYwMCwxNDgwMCIvPgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgPC9nPgogICAgPC9nPgogICA8L2c+CiAgPC9nPgogPC9nPgo8L3N2Zz4=" alt="annotations">
</div>
<div class="title">Figure 2. Annotations reflecting UML elements used by GeoAPI</div>
</div>
<div class="paragraph">
<p>Those annotations are related to the ISO 19115-1 Metadata standard in the following way:
above <code>Obligation</code> enumeration is the <code>MD_ObligationCode</code> enumeration defined by ISO 19115,
moved into the <code>opengis.annotation</code> package for making it closer to other UML-related types.
A <code>forbidden</code> value has been added for handling the cases where a property defined in a parent
interface is inapplicable to a sub-interface
(those cases are declared in ISO standards with the maximum number of occurrence set to zero).
Above <code>Stereotype</code> enumeration is a copy of the <code>MD_DatatypeCode</code> code list defined by ISO 19115,
retaining only the values relevant to GeoAPI.
This duplication exists because the ISO 19115 standard defines a code list, while Java annotations require enumerations.</p>
</div>
<details>
<summary>Annotations in Java source code</summary>
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph">
<p>All classes, interfaces and enumeration types in GeoAPI which are based on a published standard
shall have an <code>@UML</code> annotation documenting the standard in which is defined the type,
the original name of the element, and the version of the standard if different than the dated normative reference.
As an example, the annotation label for the <code>ProjectedCRS</code> interface appears in the source code as below
(except the classifier which is implicit):</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="CodeRay highlight"><code data-lang="java"><span class="annotation">@Classifier</span>(Stereotype.TYPE)
<span class="annotation">@UML</span>(identifier = <span class="string"><span class="delimiter">&quot;</span><span class="content">ProjectedCRS</span><span class="delimiter">&quot;</span></span>, specification = ISO_19111)
<span class="directive">public</span> <span class="type">interface</span> <span class="class">ProjectedCRS</span> <span class="directive">extends</span> GeneralDerivedCRS {
    ...
}</code></pre>
</div>
</div>
<div class="paragraph">
<p>which specifies that the type was defined in ISO 19111 standard as a type also named <code>ProjectedCRS</code>.
The <code>@Classifier</code> annotation is omitted when the value is <code>Stereotype.TYPE</code>, which is the most common case.
In addition, that interface contains the method <code>getCoordinateSystem()</code> with the following annotation:</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="CodeRay highlight"><code data-lang="java"><span class="annotation">@UML</span>(identifier    = <span class="string"><span class="delimiter">&quot;</span><span class="content">coordinateSystem</span><span class="delimiter">&quot;</span></span>,
     obligation    = MANDATORY,
     specification = ISO_19111)
CartesianCS getCoordinateSystem();</code></pre>
</div>
</div>
<div class="paragraph">
<p>which indicates that the method was defined in the same ISO 19111 specification
but had the name <code>coordinateSystem</code> in the standard rather than the <code>getCoordinateSystem</code> name used by GeoAPI,
and that a non-null value must be provided by every <code>ProjectedCRS</code> instance.</p>
</div>
<div class="paragraph">
<p><a href="#UML-introspection">An example in annex</a> shows how these annotations are available at runtime by introspection.</p>
</div>
</td>
</tr>
</table>
</div>
</details>
</div>
<div class="sect3">
<h4 id="derived-properties"><a class="anchor" href="#derived-properties"></a>6.1.4. Derived methods</h4>
<div class="paragraph">
<p>GeoAPI may define additional methods not explicitly specified in OGC/ISO abstract models,
when the values returned by those methods can be derived from the values provided by standard OGC/ISO properties.
Those extensions are enabled by a GeoAPI restriction in the way properties are handled.
In OGC/ISO abstract models each property could have its value stored verbatim,
for example as a column in a database table, an XML element in a file or a field in a class.
For enabling efficient use of OGS/ISO models in relational databases or XML files,
those models are generally non-redundant: each value is stored in exactly one property.
By contrast in GeoAPI all properties are getter methods;
no matter how implementations store property values, users can fetch them only through method calls.
Since methods are free to compute values from other properties,
GeoAPI uses this capability for making some information more easily accessible
in situations where property values can be reached only indirectly in OGC/ISO models.
Those additional methods introduce apparent duplications,
but they should be thought as links to the real properties rather than copies of the property values.
Those methods are added sparsely,
in places where introducing them brings some harmonization by reducing the needs to perform special cases.
Examples include fetching the head of an arbitrary <code>GenericName</code>,
fetching the Geodetic Reference Frame indirectly associated to a <code>ProjectedCRS</code>,
fetching axes of an arbitrary Coordinate Reference System (including compound ones), and more.
Those additional methods can be recognized by the absence of <code>@UML</code> annotation.</p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="core-types"><a class="anchor" href="#core-types"></a>6.2. Core data types mapping</h3>
<div class="paragraph">
<p>The ISO 19103 specification (<em>Geographic Information – Conceptual schema language</em>) defines types
which are used as building blocks by the other standards in the 19100 series.
ISO 19103:2015 defines Primitive types (§7.2 of that standard), Collection types (§7.3), Enumerated types (§7.4),
Name types (§7.5), Record types (§7.7) and Unit of Measure types (§C.4).
GeoAPI maps these types either to existing types from the Java and Python standard libraries or, when needed,
to types defined in the <code>opengis.util</code> package.
That utility package is used by GeoAPI for types defined in the ISO 19103 specification
for which no equivalence is already present in the Java and Python standard libraries.</p>
</div>
<div class="paragraph">
<p>For various practical reasons the mapping from ISO types to programming language types is not a one-to-one relationship.
The mapping actually used is explained below.
Furthermore not all of the types in ISO 19103 have a mapping defined because the need for these types has not yet appeared,
since they have not yet appeared in any other specification for which GeoAPI defines interfaces.
Such types are listed as "unimplemented" in the tables below.</p>
</div>
<div class="sect3">
<h4 id="primitives"><a class="anchor" href="#primitives"></a>6.2.1. Primitive types</h4>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §7.2.1</em></p>
</div>
<div class="paragraph">
<p>Each primitive type of the OGC/ISO specifications maps to zero, one or two object structures in GeoAPI.
Where the mapping can be made directly to a programming language primitive type, such as <code>int</code> and <code>double</code>,
the language primitive is preferred. However, when the value must be able to be set to <code>null</code>,
the object wrapper of that primitive may be used in languages where "primitives" and "wrappers" are distinct.
The following table shows the mapping used by GeoAPI to represent the primitive types in the ISO 19100 series.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 4. Primitive types mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java type</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python type</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Boolean</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.Boolean</code>       <sup>(1)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>int</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.Number</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>int</code> or <code>float</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Integer</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.Integer</code>    <sup>(1)(2)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>int</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Real</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.Double</code>        <sup>(1)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>float</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Decimal</code> <sup>(3)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.math.BigDecimal</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>float</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Vector</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">unimplemented</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>CharacterString</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.String</code>        <sup>(4)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>str</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Sequence&lt;Character&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.CharSequence</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>str</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>CharacterSetCode</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.nio.charset.Charset</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p><span class="small">(1) The primitive type, such as <code>int</code> and <code>double</code>, is used instead of the wrapper where the value can not be <code>null</code>.</span><br>
<span class="small">(2) Sometime substituted by <code>java.lang.Long</code> or <code>long</code> where the value may exceed 2<sup>32</sup>.</span><br>
<span class="small">(3) <code>Decimal</code> differs from <code>Real</code>, as <code>Decimal</code> is exact in base 10 while <code>Real</code> may not.</span><br>
<span class="small">(4) Substituted by <code>org.opengis.util.InternationalString</code> where the string representation depends on the locale.</span><br></p>
</div>
</div>
<div class="sect3">
<h4 id="datetime"><a class="anchor" href="#datetime"></a>6.2.2. Date and time</h4>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §7.2.2 to 7.2.4</em></p>
</div>
<div class="paragraph">
<p>The ISO 19103 <code>Date</code> interface gives values for year, month and day
while the <code>Time</code> interface gives values for hour, minute and second.
<code>DateTime</code> is the combination of a date with a time, with or without timezone.
GeoAPI maps the ISO date and time interfaces to the types provided in the standard library of target languages.
In some cases like Java, this mapping forces GeoAPI to choose whether the time component shall include timezone
information or not since the choices are represented by different types
(e.g. <code>LocalDateTime</code>, <code>OffsetDateTime</code> and <code>ZonedDateTime</code>).
The timezone information is often desired for geospatial data
(for example in the acquisition time of a remote sensing image),
but may be undesired for some other cases like office opening hours.
In the later case, the decision to include timezone or not depends if the opening hours apply to one specific office
or to all offices spanning the multiple timezones of a country.
GeoAPI generally includes timezone information, but this policy may be adjusted on a case-by-case basis.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 5. Date and time types mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java class</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python type</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Date</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.time.LocalDate</code>     <sup>(1)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Time</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.time.OffsetTime</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>DateTime</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.time.ZonedDateTime</code> <sup>(1)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>datetime</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">(none)</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.time.Instant</code>       <sup>(1)</sup></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p><span class="small">(1) Some properties defined in GeoAPI 3.<em>x</em> use the legacy <code>java.util.Date</code> class for historical reasons.</span><br></p>
</div>
<div class="paragraph">
<p><strong>Note:</strong> <code>DateTime</code> is distinct from <code>Instant</code>.
The former is expressed in the proleptic Gregorian calendar as described in ISO 8601,
while the later is an instantaneous point on the selected time scale, astronomical or atomic.
An <code>Instant</code> does not have year, month or day components
(it is instead a duration elapsed since an epoch),
and its conversion to a <code>DateTime</code> may be complicated.
In GeoAPI, temporal objects in metadata are typically <code>DateTime</code>
while coordinates in a temporal coordinate reference system are typically <code>Instant</code>.</p>
</div>
</div>
<div class="sect3">
<h4 id="collections"><a class="anchor" href="#collections"></a>6.2.3. Collections</h4>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §7.3</em></p>
</div>
<div class="paragraph">
<p>GeoAPI implements ISO 19103 collection interfaces using the standard Collections Frameworks provided by Java and Python.
A <code>Set</code> is a finite collection of objects where each object appears only once.
A <code>Bag</code> is similar to a <code>Set</code> except that it may contain duplicated instances.
The order of elements in a <code>Set</code> or a <code>Bag</code> is not specified.
A <code>Sequence</code> is similar to a <code>Bag</code> except that elements are ordered.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 6. Collections mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java type</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python type</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Collection</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.util.Collection</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Sequence</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Bag</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.util.Collection</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Set</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.util.Set</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Sequence</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.util.List</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Sequence</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Dictionary</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.util.Map</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>Unless otherwise required by the semantic of a property, GeoAPI preferably uses the <code>Collection</code> type in Java method signatures.
This allows implementors to choose their preferred subtypes, usually <code>Set</code> or <code>Sequence</code>.
The <code>Set</code> type is not the default type because enforcing element uniqueness may constraint implementations to use hash tables
or similar algorithms, which is not always practical.</p>
</div>
</div>
<div class="sect3">
<h4 id="controlled-vocabulary"><a class="anchor" href="#controlled-vocabulary"></a>6.2.4. Controlled vocabulary</h4>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §6.5</em></p>
</div>
<div class="paragraph">
<p>GeoAPI distinguishes between two enumerated types depending on whether the complete set of literal types is known
when the code is originally created, or if the list may be extended at run time.
Many language provides an <code>enum</code> construct for the former case and
GeoAPI defines the <code>CodeList</code> abstract class in Java for the later case.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 7. Enumerated types mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 type</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java type</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python type</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">CodeList</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.CodeList</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Enumeration</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.Enum</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Enum</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Bit</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Digit</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Sign</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>In some specifications (for example ISO 19115), code list and enumeration names end with the <code>Code</code> suffix.
Some other specifications (for example ISO 19111) do not use any particular suffix.
The mapping to programmatic API may uniformize those type names to a single convention, depending on the target language.
For the Java API, <code>Code</code> suffixes are omitted in class names.
For the Python API, class names are left unchanged.</p>
</div>
<details>
<summary>Code lists in Java</summary>
<div class="admonitionblock caution">
<table>
<tr>
<td class="icon">
<i class="fa icon-caution" title="Caution"></i>
</td>
<td class="content">
<div class="paragraph">
<p>The use of <code>org.opengis.util.CodeList</code> constructs includes accessing statically defined elements,
defining new elements and retrieving any element defined for the code list.
Considering, for example, <code>org.​opengis.​referencing.​cs.​AxisDirection</code>,
the following code could be used:</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="CodeRay highlight"><code data-lang="java">AxisDirection north    = AxisDirection.NORTH;
AxisDirection northBis = AxisDirection.valueOf(<span class="string"><span class="delimiter">&quot;</span><span class="content">NORTH</span><span class="delimiter">&quot;</span></span>);</code></pre>
</div>
</div>
<div class="paragraph">
<p>where the second locution will create a new value if it does not exist.
Special care should be taken to keep such calls consistent throughout the code
since the <code>CodeList</code> will create a new element if there are any difference in the <code>String</code> parameters.</p>
</div>
</td>
</tr>
</table>
</div>
</details>
</div>
<div class="sect3">
<h4 id="generic-name"><a class="anchor" href="#generic-name"></a>6.2.5. Name types</h4>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §7.5</em></p>
</div>
<div class="paragraph">
<p>A name is a sequence of identifiers rooted within the context of a namespace.
<code>NameSpace</code> defines a domain in which "names" given by character strings can be mapped to objects.
For example a class forms a namespace for the properties that the class contains.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 8. Name types mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python class</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">(constructors)</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.NameFactory</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>NameSpace</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.NameSpace</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>GenericName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.GenericName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>ScopedName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.ScopedName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LocalName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.LocalName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>TypeName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.TypeName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MemberName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.MemberName</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p><code>GenericName</code> is the base interface for all names in a <code>NameSpace</code>.
A generic name can be either a <code>LocalName</code>, or a <code>ScopedName</code> which is a composite of
a <code>LocalName</code> (the <em>head</em>) for locating another <code>NameSpace</code> and a <code>GenericName</code> (the <em>tail</em>) valid in that name space.
For example if <code>"urn:​ogc:​def:​crs:​EPSG:​9.5:​4326"</code> is a <code>ScopedName</code>,
then <code>"urn"</code> is the <em>head</em> and <code>"ogc:​def:​crs:​EPSG:​9.5:​4326"</code> is the <em>tail</em>.
GeoAPI extends the model by allowing navigation in the opposite direction,
with <code>"urn:​ogc:​def:​crs:​EPSG:​9.5"</code> as the <em>path</em> and <code>"4326"</code> as the <em>tip</em>.</p>
</div>
<div class="paragraph">
<p><code>TypeName</code> and <code>MemberName</code> are subtypes of <code>LocalName</code>
for referencing a type (for example a class) and a member (for example a property in a class) respectively.
The <code>NameFactory</code> is an extension of the GeoAPI project designed to allow the construction of instances of these name types.</p>
</div>
<div class="imageblock">
<div class="content">
<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj4KPHN2ZyB2ZXJzaW9uPSIxLjIiIHdpZHRoPSIxNzAuM21tIiBoZWlnaHQ9IjEwNS4zbW0iIHZpZXdCb3g9Ijk4NSA5ODUgMTcwMzAgMTA1MzAiIHByZXNlcnZlQXNwZWN0UmF0aW89InhNaWRZTWlkIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIHN0cm9rZS13aWR0aD0iMjguMjIyIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOm9vbz0iaHR0cDovL3htbC5vcGVub2ZmaWNlLm9yZy9zdmcvZXhwb3J0IiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgeG1sbnM6cHJlc2VudGF0aW9uPSJodHRwOi8vc3VuLmNvbS94bWxucy9zdGFyb2ZmaWNlL3ByZXNlbnRhdGlvbiIgeG1sbnM6c21pbD0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9TTUlMMjAvIiB4bWxuczphbmltPSJ1cm46b2FzaXM6bmFtZXM6dGM6b3BlbmRvY3VtZW50OnhtbG5zOmFuaW1hdGlvbjoxLjAiIHhtbDpzcGFjZT0icHJlc2VydmUiPgogPGRlZnMgY2xhc3M9IkNsaXBQYXRoR3JvdXAiPgogIDxjbGlwUGF0aCBpZD0icHJlc2VudGF0aW9uX2NsaXBfcGF0aCIgY2xpcFBhdGhVbml0cz0idXNlclNwYWNlT25Vc2UiPgogICA8cmVjdCB4PSI5ODUiIHk9Ijk4NSIgd2lkdGg9IjE3MDMwIiBoZWlnaHQ9IjEwNTMwIi8+CiAgPC9jbGlwUGF0aD4KICA8Y2xpcFBhdGggaWQ9InByZXNlbnRhdGlvbl9jbGlwX3BhdGhfc2hyaW5rIiBjbGlwUGF0aFVuaXRzPSJ1c2VyU3BhY2VPblVzZSI+CiAgIDxyZWN0IHg9IjEwMDIiIHk9Ijk5NSIgd2lkdGg9IjE2OTk2IiBoZWlnaHQ9IjEwNTA5Ii8+CiAgPC9jbGlwUGF0aD4KIDwvZGVmcz4KIDxkZWZzPgogIDxmb250IGlkPSJFbWJlZGRlZEZvbnRfMSIgaG9yaXotYWR2LXg9IjIwNDgiPgogICA8Zm9udC1mYWNlIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyBlbWJlZGRlZCIgdW5pdHMtcGVyLWVtPSIyMDQ4IiBmb250LXdlaWdodD0ibm9ybWFsIiBmb250LXN0eWxlPSJub3JtYWwiIGFzY2VudD0iMTg3MCIgZGVzY2VudD0iNDM5Ii8+CiAgIDxtaXNzaW5nLWdseXBoIGhvcml6LWFkdi14PSIyMDQ4IiBkPSJNIDAsMCBMIDIwNDcsMCAyMDQ3LDIwNDcgMCwyMDQ3IDAsMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSLCoCIgaG9yaXotYWR2LXg9IjEiLz4KICAgPGdseXBoIHVuaWNvZGU9InkiIGhvcml6LWFkdi14PSI5NzEiIGQ9Ik0gMTA2NSwxMDQyIEwgNjcwLDAgQyA2MTMsLTE1MyA1NTYsLTI2MCA1MDAsLTMyMSA0NDMsLTM4MiAzNzksLTQxMiAzMDcsLTQxMiAyNDMsLTQxMiAxODAsLTQwMCAxMTcsLTM3NyBMIDE0MywtMjUyIEMgMTk0LC0yNjggMjM5LC0yNzYgMjc5LC0yNzYgMzM1LC0yNzYgMzgxLC0yNTQgNDE3LC0yMTEgNDUzLC0xNjggNDg5LC05NyA1MjYsMiBMIDEzNSwxMDQyIDMwNSwxMDQyIDYwMCwyMTEgODk1LDEwNDIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0idSIgaG9yaXotYWR2LXg9IjgzMiIgZD0iTSAxMDE2LDAgTCA4NjAsMCA4NjAsMTU0IEMgNzQ4LDM1IDYzMCwtMjUgNTA2LC0yNSAzMTEsLTI1IDIxMyw5MyAyMTMsMzMwIEwgMjEzLDEwNDIgMzY5LDEwNDIgMzY5LDM3NSBDIDM2OSwyODYgMzgzLDIyMCA0MTEsMTc3IDQzOSwxMzMgNDg2LDExMSA1NTMsMTExIDY0OCwxMTEgNzUxLDE3MCA4NjAsMjg5IEwgODYwLDEwNDIgMTAxNiwxMDQyIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InQiIGhvcml6LWFkdi14PSI4MDkiIGQ9Ik0gMTAyOCw0NyBDIDkyMywtMSA4MjAsLTI1IDcxOSwtMjUgNTMzLC0yNSA0NDAsNzMgNDQwLDI3MCBMIDQ0MCw5MDcgMjQwLDkwNyAyNDAsMTA0MiA0NDAsMTA0MiA0NDAsMTMyNyA1OTIsMTMyNyA1OTIsMTA0MiA5NDYsMTA0MiA5NDYsOTA3IDU5Miw5MDcgNTkyLDMxMSBDIDU5MiwyNDggNjA2LDE5OSA2MzQsMTY0IDY2MiwxMjkgNzAwLDExMSA3NDgsMTExIDgzMywxMTEgOTE4LDEzMCAxMDAxLDE2OCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJzIiBob3Jpei1hZHYteD0iNzg2IiBkPSJNIDkwOSw4OTMgQyA4MjIsOTIyIDczOSw5MzYgNjYyLDkzNiA1NzcsOTM2IDUxMCw5MjIgNDYxLDg5NCA0MTIsODY1IDM4Nyw4MjcgMzg3LDc4MCAzODcsNzUwIDM5OCw3MjQgNDIxLDcwMiA0NDQsNjc5IDQ4OSw2NTggNTU3LDYzOSBMIDcwNiw1OTYgQyA3NzUsNTc3IDgzMSw1NTQgODc0LDUyNyA5MTcsNTAwIDk1MCw0NjcgOTcyLDQyOSA5OTMsMzkwIDEwMDQsMzQ2IDEwMDQsMjk1IDEwMDQsMTk2IDk2MiwxMTcgODc5LDYwIDc5NiwyIDY5MCwtMjcgNTYxLC0yNyA0MzMsLTI3IDMyNywtOCAyNDQsMzEgTCAyNzQsMTY2IEMgMzYxLDEyNSA0NTUsMTA0IDU1NSwxMDQgNjQyLDEwNCA3MTIsMTIwIDc2NywxNTMgODIxLDE4NSA4NDgsMjI4IDg0OCwyODEgODQ4LDMxNyA4MzUsMzQ5IDgwOCwzNzggNzgxLDQwNiA3MTcsNDM1IDYxNyw0NjYgNTM2LDQ5MSA0NzcsNTA5IDQzOSw1MjIgNDAxLDUzNCAzNjYsNTUyIDMzMyw1NzYgMzAwLDYwMCAyNzYsNjI5IDI1OSw2NjIgMjQyLDY5NSAyMzMsNzMzIDIzMyw3NzQgMjMzLDg2NCAyNzIsOTM1IDM1MSw5ODggNDMwLDEwNDEgNTM0LDEwNjcgNjY0LDEwNjcgNzQ4LDEwNjcgODQxLDEwNTMgOTQ0LDEwMjQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iciIgaG9yaXotYWR2LXg9IjcxNyIgZD0iTSAxMDM0LDEwMTQgTCA5NzEsODY0IEMgOTAyLDg5NCA4NDMsOTA5IDc5Myw5MDkgNzQ3LDkwOSA3MDIsODk2IDY1OCw4NzAgNjE0LDg0MyA1NzQsODA2IDUzNyw3NTcgNTAwLDcwOCA0ODEsNjgwIDQ4MSw2NzQgTCA0ODEsMCAzMjYsMCAzMjYsMTA0MiA0ODEsMTA0MiA0ODEsODMxIEMgNTY0LDk4OCA2NzIsMTA2NyA4MDUsMTA2NyA4ODQsMTA2NyA5NjAsMTA0OSAxMDM0LDEwMTQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0icSIgaG9yaXotYWR2LXg9Ijg3OCIgZD0iTSAxMDE0LC00MTIgTCA4NTgsLTQxMiA4NTgsMTUwIEMgODI2LDk5IDc4MCw1NiA3MjEsMjMgNjYxLC0xMCA1OTgsLTI3IDUzMiwtMjcgNDEzLC0yNyAzMTgsMjUgMjQ5LDEzMCAxODAsMjM1IDE0NSwzNjggMTQ1LDUzMCAxNDUsNjg1IDE4MSw4MTQgMjUzLDkxNSAzMjUsMTAxNiA0MjEsMTA2NyA1NDEsMTA2NyA2NzIsMTA2NyA3NzgsMTAxNCA4NTgsOTA3IEwgODU4LDEwNDIgMTAxNCwxMDQyIFogTSA4NTgsMzE5IEwgODU4LDc1MiBDIDc4Myw4NzIgNjkzLDkzMiA1ODgsOTMyIDUwMiw5MzIgNDM0LDg5MyAzODQsODE1IDMzNCw3MzcgMzA5LDYzOSAzMDksNTIyIDMwOSw0MDMgMzMzLDMwNSAzODAsMjI4IDQyNywxNTEgNDkxLDExMyA1NzMsMTEzIDYxMiwxMTMgNjUyLDEyNCA2OTIsMTQ1IDczMiwxNjYgNzcwLDE5OCA4MDUsMjQxIDg0MCwyODQgODU4LDMxMCA4NTgsMzE5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InAiIGhvcml6LWFkdi14PSI4NzgiIGQ9Ik0gMzcxLDkwNyBDIDQwNiw5NTUgNDUzLDk5NCA1MTIsMTAyMyA1NzEsMTA1MiA2MzMsMTA2NyA2OTgsMTA2NyA4MTgsMTA2NyA5MTIsMTAxOCA5ODEsOTE5IDEwNDksODIwIDEwODMsNjkxIDEwODMsNTMyIDEwODMsMzcyIDEwNDcsMjM5IDk3NiwxMzMgOTA0LDI2IDgwNywtMjcgNjg2LC0yNyA2MjEsLTI3IDU2MSwtMTEgNTA1LDIyIDQ0OCw1NCA0MDQsOTcgMzcxLDE1MCBMIDM3MSwtNDEyIDIxNSwtNDEyIDIxNSwxMDQyIDM3MSwxMDQyIFogTSAzNzEsNzUyIEwgMzcxLDMxOSBDIDQwMCwyNjAgNDQwLDIxMSA0OTAsMTcyIDU0MCwxMzMgNTkyLDExMyA2NDUsMTEzIDcyOSwxMTMgNzk2LDE1MiA4NDYsMjMxIDg5NSwzMTAgOTIwLDQwNyA5MjAsNTI0IDkyMCw2NDQgODk2LDc0MiA4NDksODE4IDgwMiw4OTQgNzM2LDkzMiA2NTEsOTMyIDU0MSw5MzIgNDQ4LDg3MiAzNzEsNzUyIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im8iIGhvcml6LWFkdi14PSI5NDciIGQ9Ik0gNjE0LDEwNjcgQyA3NTcsMTA2NyA4NzEsMTAxNyA5NTYsOTE3IDEwNDEsODE3IDEwODMsNjg1IDEwODMsNTIyIDEwODMsMzU3IDEwNDEsMjI1IDk1NiwxMjQgODcxLDIzIDc1NywtMjcgNjE0LC0yNyA0NzAsLTI3IDM1NiwyMyAyNzIsMTI0IDE4NywyMjQgMTQ1LDM1NyAxNDUsNTIyIDE0NSw2ODYgMTg3LDgxOCAyNzIsOTE4IDM1NywxMDE3IDQ3MSwxMDY3IDYxNCwxMDY3IFogTSA2MTQsMTA5IEMgNzA5LDEwOSA3ODMsMTQ5IDgzOCwyMjkgODkzLDMwOSA5MjAsNDA2IDkyMCw1MjAgOTIwLDY0MCA4OTMsNzM5IDgzOSw4MTYgNzg1LDg5MyA3MTAsOTMyIDYxNCw5MzIgNTE3LDkzMiA0NDIsODk0IDM4OSw4MTggMzM2LDc0MSAzMDksNjQyIDMwOSw1MjAgMzA5LDQwNCAzMzYsMzA3IDM5MCwyMjggNDQ0LDE0OSA1MTksMTA5IDYxNCwxMDkgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibiIgaG9yaXotYWR2LXg9IjgzMiIgZD0iTSAxMDE4LDAgTCA4NjIsMCA4NjIsNjY4IEMgODYyLDc2MSA4NDcsODI4IDgxOCw4NzAgNzg4LDkxMSA3NDEsOTMyIDY3OCw5MzIgNTgzLDkzMiA0ODAsODczIDM3MSw3NTQgTCAzNzEsMCAyMTUsMCAyMTUsMTA0MiAzNzEsMTA0MiAzNzEsODg5IEMgNDgzLDEwMDggNjAxLDEwNjcgNzI1LDEwNjcgOTIwLDEwNjcgMTAxOCw5NDkgMTAxOCw3MTMgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibSIgaG9yaXotYWR2LXg9Ijk5NCIgZD0iTSAxMTAwLDAgTCA5NDQsMCA5NDQsNjg0IEMgOTQ0LDg0OSA5MTMsOTMyIDg1Miw5MzIgODIxLDkzMiA3ODYsOTA0IDc0OSw4NDcgNzExLDc5MCA2OTIsNzU4IDY5Miw3NTIgTCA2OTIsMCA1MzcsMCA1MzcsNjg0IEMgNTM3LDg0OSA1MDYsOTMyIDQ0NCw5MzIgNDEzLDkzMiAzNzksOTA0IDM0Miw4NDcgMzA0LDc5MCAyODUsNzU4IDI4NSw3NTIgTCAyODUsMCAxMjksMCAxMjksMTA0MiAyNzksMTA0MiAyNzksODg1IEMgMzA4LDk0MCAzNDMsOTg1IDM4NCwxMDE4IDQyNCwxMDUxIDQ2NCwxMDY3IDUwNCwxMDY3IDU0NiwxMDY3IDU4NCwxMDUxIDYxNywxMDE4IDY1MCw5ODUgNjczLDk0MCA2ODYsODg1IDcxNSw5NDAgNzUwLDk4NSA3OTEsMTAxOCA4MzIsMTA1MSA4NzIsMTA2NyA5MTEsMTA2NyAxMDM3LDEwNjcgMTEwMCw5NTQgMTEwMCw3MjkgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibCIgaG9yaXotYWR2LXg9IjcxNyIgZD0iTSA5NDIsMCBMIDUxNiwwIDUxNiwxMjc2IDI0NCwxMjc2IDI0NCwxNDExIDY3MiwxNDExIDY3MiwxMzUgOTQyLDEzNSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJpIiBob3Jpei1hZHYteD0iNTMyIiBkPSJNIDY4MiwxNDM2IEMgNzExLDE0MzYgNzM2LDE0MjYgNzU3LDE0MDUgNzc4LDEzODQgNzg4LDEzNTggNzg4LDEzMjkgNzg4LDEyOTggNzc4LDEyNzMgNzU4LDEyNTIgNzM3LDEyMzEgNzEyLDEyMjEgNjgyLDEyMjEgNjU1LDEyMjEgNjMxLDEyMzIgNjEwLDEyNTQgNTg5LDEyNzUgNTc4LDEzMDAgNTc4LDEzMjkgNTc4LDEzNTggNTg5LDEzODMgNjEwLDE0MDQgNjMxLDE0MjUgNjU1LDE0MzYgNjgyLDE0MzYgWiBNIDc1OCwwIEwgNjAyLDAgNjAyLDkwNyAyOTUsOTA3IDI5NSwxMDQyIDc1OCwxMDQyIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImgiIGhvcml6LWFkdi14PSI4MzIiIGQ9Ik0gMTAxOCwwIEwgODYyLDAgODYyLDY2OCBDIDg2Miw3NjEgODQ3LDgyOCA4MTgsODcwIDc4OCw5MTEgNzQxLDkzMiA2NzgsOTMyIDU4Myw5MzIgNDgwLDg3MyAzNzEsNzU0IEwgMzcxLDAgMjE1LDAgMjE1LDE0MTEgMzcxLDE0MTEgMzcxLDg4OSBDIDQ4MywxMDA4IDYwMSwxMDY3IDcyNSwxMDY3IDkyMCwxMDY3IDEwMTgsOTQ5IDEwMTgsNzEzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImciIGhvcml6LWFkdi14PSI5NDciIGQ9Ik0gNTA0LDM5MyBDIDQwNywzNDYgMzU4LDMwNCAzNTgsMjY4IDM1OCwyNDkgMzY5LDIzMiAzOTIsMjE5IDQxNCwyMDYgNDQ0LDE5OSA0ODMsMTk5IEwgODM0LDIwOSBDIDkxNywyMDkgOTgyLDE4OSAxMDI5LDE0OCAxMDc1LDEwNyAxMDk4LDUwIDEwOTgsLTI1IDEwOTgsLTE0MyAxMDQ1LC0yMzcgOTQwLC0zMDcgODM1LC0zNzcgNzEyLC00MTIgNTcxLC00MTIgNDU2LC00MTIgMzYxLC0zODggMjg4LC0zNDAgMjE1LC0yOTEgMTc4LC0yMjkgMTc4LC0xNTIgMTc4LC02OSAyMzUsMiAzNDgsNTkgMjU0LDEwMyAyMDcsMTU3IDIwNywyMjEgMjA3LDI5NCAyNjgsMzYzIDM4OSw0MjYgMzMzLDQ2MCAyOTAsNTA0IDI2MCw1NTggMjMwLDYxMiAyMTUsNjY5IDIxNSw3MjkgMjE1LDgyNyAyNDgsOTA4IDMxNSw5NzEgMzgyLDEwMzQgNDY1LDEwNjUgNTY1LDEwNjUgNjM3LDEwNjUgNzAyLDEwNTAgNzYwLDEwMjAgTCAxMDY5LDEwNjcgMTA2OSw5MTUgODcwLDkxNSBDIDkwMCw4NjIgOTE1LDgwMCA5MTUsNzI5IDkxNSw2MzQgODgyLDU1NCA4MTYsNDg5IDc1MCw0MjQgNjY2LDM5MSA1NjUsMzkxIDU0NCwzOTEgNTIzLDM5MiA1MDQsMzkzIFogTSA1NjUsNTEyIEMgNjIyLDUxMiA2NjksNTMyIDcwNiw1NzIgNzQyLDYxMSA3NjAsNjYzIDc2MCw3MjcgNzYwLDc5NCA3NDIsODQ3IDcwNyw4ODUgNjcyLDkyMyA2MjQsOTQyIDU2NSw5NDIgNTEwLDk0MiA0NjMsOTIyIDQyNiw4ODMgMzg4LDg0MyAzNjksNzkxIDM2OSw3MjcgMzY5LDY2NCAzODgsNjEzIDQyNiw1NzMgNDYzLDUzMiA1MTAsNTEyIDU2NSw1MTIgWiBNIDU3MSwtMjkxIEMgNjc0LC0yOTEgNzYxLC0yNjggODM0LC0yMjMgOTA2LC0xNzcgOTQyLC0xMjAgOTQyLC01MyA5NDIsMTYgODkxLDUxIDc4OCw1MSA3NTQsNTEgNjk1LDQ5IDYxMiw0NSA1NTksNDIgNTE4LDQxIDQ4OCw0MSA0NzMsNDEgNDUxLDI5IDQyMCw1IDM4OSwtMTkgMzY1LC00MyAzNDksLTY4IDMzMiwtOTIgMzI0LC0xMTcgMzI0LC0xNDMgMzI0LC0xODQgMzQ3LC0yMTkgMzkyLC0yNDggNDM3LC0yNzcgNDk3LC0yOTEgNTcxLC0yOTEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZiIgaG9yaXotYWR2LXg9IjgzMiIgZD0iTSAxMDI4LDEzOTUgTCA5ODcsMTI2OCBDIDkxMywxMjg5IDg0NywxMzAwIDc4OCwxMzAwIDczMiwxMzAwIDY5NCwxMjg0IDY3MywxMjUzIDY1MiwxMjIxIDY0MSwxMTU3IDY0MSwxMDYxIEwgNjQxLDEwNDIgOTkxLDEwNDIgOTkxLDkwNyA2NDEsOTA3IDY0MSwwIDQ4NSwwIDQ4NSw5MDcgMjIzLDkwNyAyMjMsMTA0MiA0ODUsMTA0MiA0ODUsMTA3NyBDIDQ4NSwxMjEwIDUwOCwxMzAzIDU1MywxMzU2IDU5OCwxNDA5IDY3NywxNDM2IDc4OCwxNDM2IDg2MCwxNDM2IDk0MCwxNDIyIDEwMjgsMTM5NSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJlIiBob3Jpei1hZHYteD0iOTAxIiBkPSJNIDEwNDksNTE2IEwgMzM0LDUxNiBDIDMzNCwzOTggMzY4LDMwMSA0MzcsMjI0IDUwNSwxNDcgNTg5LDEwOSA2ODgsMTA5IDc4MywxMDkgODgwLDEyNiA5ODEsMTYwIEwgMTAwOCwzNSBDIDkxNiwtNiA4MDksLTI3IDY4NiwtMjcgNTMxLC0yNyA0MDcsMjMgMzEyLDEyMiAyMTcsMjIxIDE3MCwzNTUgMTcwLDUyNCAxNzAsNjg5IDIxMyw4MjEgMjk4LDkyMCAzODMsMTAxOCA0OTIsMTA2NyA2MjcsMTA2NyA3NDcsMTA2NyA4NDcsMTAyMSA5MjgsOTMwIDEwMDksODM5IDEwNDksNzE3IDEwNDksNTY1IFogTSA4NzksNjQzIEMgODc5LDcyNCA4NTQsNzkzIDgwMyw4NDkgNzUyLDkwNCA2OTYsOTMyIDYzMyw5MzIgNTUyLDkzMiA0ODYsOTA1IDQzMyw4NTIgMzgwLDc5OSAzNTAsNzI5IDM0NCw2NDMgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZCIgaG9yaXotYWR2LXg9Ijg3OCIgZD0iTSAxMDE0LDAgTCA4NTgsMCA4NTgsMTUwIEMgODI2LDk5IDc4MCw1NiA3MjEsMjMgNjYxLC0xMCA1OTgsLTI3IDUzMiwtMjcgNDEzLC0yNyAzMTgsMjYgMjQ5LDEzMyAxODAsMjM5IDE0NSwzNzQgMTQ1LDUzNyAxNDUsNjkwIDE4MSw4MTYgMjU0LDkxNyAzMjYsMTAxNyA0MjIsMTA2NyA1NDEsMTA2NyA2NzIsMTA2NyA3NzgsMTAxNCA4NTgsOTA3IEwgODU4LDE0MTEgMTAxNCwxNDExIFogTSA4NTgsMzE5IEwgODU4LDc1MiBDIDc4Myw4NzIgNjkzLDkzMiA1ODgsOTMyIDUwMiw5MzIgNDM0LDg5NCAzODQsODE4IDMzNCw3NDEgMzA5LDY0NSAzMDksNTI4IDMwOSw0MDggMzMzLDMwOSAzODAsMjMxIDQyNywxNTIgNDkxLDExMyA1NzMsMTEzIDYxMiwxMTMgNjUyLDEyNCA2OTIsMTQ1IDczMiwxNjYgNzcwLDE5OCA4MDUsMjQxIDg0MCwyODQgODU4LDMxMCA4NTgsMzE5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImMiIGhvcml6LWFkdi14PSI4NTUiIGQ9Ik0gMTAxNCwzNSBDIDkyMywtNiA4MjQsLTI3IDcxNSwtMjcgNTUxLC0yNyA0MjAsMjMgMzIzLDEyMyAyMjUsMjIyIDE3NiwzNTUgMTc2LDUyMCAxNzYsNjg1IDIyNSw4MTggMzI0LDkxOCA0MjIsMTAxNyA1NTEsMTA2NyA3MTEsMTA2NyA4MTYsMTA2NyA5MTUsMTA0OSAxMDEwLDEwMTIgTCA5NjksODgzIEMgODc0LDkxNiA3ODksOTMyIDcxMyw5MzIgNTk4LDkzMiA1MDgsODk1IDQ0MSw4MjEgMzc0LDc0NyAzNDAsNjQ3IDM0MCw1MjAgMzQwLDQwNCAzNzUsMzA3IDQ0NCwyMjggNTEzLDE0OSA2MDQsMTA5IDcxNSwxMDkgODAzLDEwOSA4OTQsMTI2IDk4NywxNjAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iYiIgaG9yaXotYWR2LXg9Ijg3OCIgZD0iTSAzNzEsOTA3IEMgNDA2LDk1NSA0NTMsOTk0IDUxMiwxMDIzIDU3MSwxMDUyIDYzMywxMDY3IDY5OCwxMDY3IDgxOSwxMDY3IDkxNCwxMDE4IDk4Miw5MjEgMTA0OSw4MjQgMTA4Myw2OTYgMTA4Myw1MzcgMTA4MywzNzYgMTA0NywyNDIgOTc1LDEzNSA5MDMsMjcgODA3LC0yNyA2ODYsLTI3IDYyMSwtMjcgNTYxLC0xMSA1MDUsMjIgNDQ4LDU0IDQwNCw5NyAzNzEsMTUwIEwgMzcxLDAgMjE1LDAgMjE1LDE0MTEgMzcxLDE0MTEgWiBNIDM3MSw3NTIgTCAzNzEsMzE5IEMgNDAwLDI2MCA0NDAsMjExIDQ5MCwxNzIgNTQwLDEzMyA1OTIsMTEzIDY0NSwxMTMgNzI5LDExMyA3OTYsMTUzIDg0NiwyMzMgODk1LDMxMyA5MjAsNDExIDkyMCw1MjggOTIwLDY0OCA4OTYsNzQ1IDg0OSw4MjAgODAyLDg5NSA3MzYsOTMyIDY1MSw5MzIgNTQxLDkzMiA0NDgsODcyIDM3MSw3NTIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iYSIgaG9yaXotYWR2LXg9Ijg1NSIgZD0iTSAxMDQ0LDAgTCA4ODksMCBDIDg3Niw0MiA4NzAsOTggODcwLDE2OCA3ODEsMzggNjYxLC0yNyA1MDgsLTI3IDQzMSwtMjcgMzYzLDEgMzA0LDU2IDI0NSwxMTEgMjE1LDE4MSAyMTUsMjY4IDIxNSwzMTkgMjI1LDM2NSAyNDYsNDA0IDI2Nyw0NDMgMjk3LDQ3NyAzMzcsNTA0IDM3Nyw1MzEgNDI2LDU1MiA0ODMsNTY4IDU0MCw1ODMgNjA4LDU5NCA2ODcsNjAwIEwgODYyLDYxNCA4NjIsNjY0IEMgODYyLDg0MyA3NzksOTMyIDYxMiw5MzIgNTczLDkzMiA1MjMsOTI0IDQ2Myw5MDcgNDAyLDg5MCAzNTAsODY5IDMwNyw4NDYgTCAyNjYsOTU4IEMgNDA4LDEwMzEgNTQwLDEwNjcgNjYyLDEwNjcgODk5LDEwNjcgMTAxOCw5NDEgMTAxOCw2OTAgTCAxMDE4LDI0MiBDIDEwMTgsMTUxIDEwMjcsNzEgMTA0NCwwIFogTSA4NjIsMzA5IEwgODYyLDQ5OCBDIDcyMSw0ODUgNjI1LDQ3NCA1NzMsNDYzIDUyMCw0NTIgNDc1LDQzMSA0MzcsNDAxIDM5OCwzNzEgMzc5LDMzMCAzNzksMjc5IDM3OSwyMzIgMzk1LDE5NSA0MjgsMTY2IDQ2MSwxMzcgNTAyLDEyMyA1NTMsMTIzIDYwOCwxMjMgNjYzLDE0MCA3MTgsMTczIDc3MywyMDYgODIxLDI1MSA4NjIsMzA5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IlQiIGhvcml6LWFkdi14PSIxMDY0IiBkPSJNIDExMzMsMTI3OCBMIDY5OCwxMjc4IDY5OCwwIDUzNSwwIDUzNSwxMjc4IDk4LDEyNzggOTgsMTQxMSAxMTMzLDE0MTEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iUyIgaG9yaXotYWR2LXg9IjgzMiIgZD0iTSA5MTUsMTI1NSBDIDgzMSwxMjg0IDc1MSwxMjk4IDY3NiwxMjk4IDU5MSwxMjk4IDUyMSwxMjc5IDQ2OCwxMjQwIDQxNCwxMjAxIDM4NywxMTQ1IDM4NywxMDczIDM4Nyw5ODggNDU3LDkxMyA1OTgsODQ4IDcwMiw4MDAgNzcxLDc2NyA4MDUsNzQ4IDgzOCw3MjkgODcyLDcwNCA5MDcsNjcxIDk0Miw2MzggOTcwLDYwMCA5OTEsNTU2IDEwMTIsNTExIDEwMjIsNDYxIDEwMjIsNDA2IDEwMjIsMjY5IDk3NiwxNjIgODg0LDg3IDc5MSwxMSA2ODIsLTI3IDU1NywtMjcgNDM2LC0yNyAzMjksLTMgMjM4LDQ1IEwgMjcyLDE4NiBDIDM3OSwxMzcgNDczLDExMyA1NTMsMTEzIDY0NiwxMTMgNzIxLDEzNyA3NzgsMTg2IDgzNCwyMzQgODYyLDMwMCA4NjIsMzg1IDg2Miw0NDggODQ0LDUwMSA4MDcsNTQ1IDc3MCw1ODggNzAzLDYzMyA2MDQsNjc4IDUwMyw3MjUgNDM3LDc1NyA0MDgsNzc0IDM3OCw3OTEgMzQ4LDgxNCAzMTksODQ0IDI5MCw4NzQgMjY3LDkwOCAyNTEsOTQ1IDIzNSw5ODIgMjI3LDEwMjMgMjI3LDEwNjcgMjI3LDExODQgMjY5LDEyNzUgMzUzLDEzNDAgNDM3LDE0MDUgNTQzLDE0MzggNjcyLDE0MzggNzY2LDE0MzggODU5LDE0MjQgOTUyLDEzOTUgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iUSIgaG9yaXotYWR2LXg9IjEwNDEiIGQ9Ik0gMTEzNSwtMjMzIEMgMTA3NywtMjUxIDEwMjAsLTI2MCA5NjMsLTI2MCA4MTYsLTI2MCA3MjEsLTE4MCA2NzgsLTIwIDY1MiwtMjUgNjMwLC0yNyA2MTIsLTI3IDQ1NSwtMjcgMzM1LDM2IDI1MiwxNjEgMTY5LDI4NiAxMjcsNDY3IDEyNyw3MDUgMTI3LDk0MyAxNjgsMTEyNSAyNTEsMTI1MCAzMzQsMTM3NSA0NTQsMTQzOCA2MTIsMTQzOCA3NzAsMTQzOCA4OTEsMTM3NiA5NzQsMTI1MSAxMDU3LDExMjYgMTA5OCw5NDQgMTA5OCw3MDUgMTA5OCwzNTggMTAxMSwxMzMgODM4LDI5IDg2MywtNjggOTEwLC0xMTcgOTc5LC0xMTcgMTAxOCwtMTE3IDEwNjAsLTExMiAxMTA2LC0xMDIgWiBNIDYxMiwxMTcgQyA4MjAsMTE3IDkyNCwzMTMgOTI0LDcwNSA5MjQsMTA5OCA4MjAsMTI5NCA2MTIsMTI5NCA1MTEsMTI5NCA0MzQsMTI0MyAzODEsMTE0MiAzMjgsMTA0MCAzMDEsODk0IDMwMSw3MDUgMzAxLDUxNCAzMjcsMzY4IDM4MCwyNjggNDMzLDE2NyA1MTAsMTE3IDYxMiwxMTcgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iTiIgaG9yaXotYWR2LXg9Ijg3OCIgZD0iTSAxMDQwLDAgTCA4NTgsMCAzNTIsMTExNCAzNTIsMCAxODgsMCAxODgsMTQxMSAzNzEsMTQxMSA4NzksMzA3IDg3OSwxNDExIDEwNDAsMTQxMSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJMIiBob3Jpei1hZHYteD0iNzg2IiBkPSJNIDEwMjQsMCBMIDI1NiwwIDI1NiwxNDExIDQyMCwxNDExIDQyMCwxNDMgMTAyNCwxNDMgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iSSIgaG9yaXotYWR2LXg9IjY3MSIgZD0iTSA5MjgsMCBMIDI4NywwIDI4NywxNDMgNTMwLDE0MyA1MzAsMTI2OCAyODcsMTI2OCAyODcsMTQxMSA5MjgsMTQxMSA5MjgsMTI2OCA2OTQsMTI2OCA2OTQsMTQzIDkyOCwxNDMgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iRyIgaG9yaXotYWR2LXg9Ijk3MSIgZD0iTSAxMDY5LDEwMCBDIDk1NCwxNSA4MjYsLTI3IDY4NiwtMjcgNTAzLC0yNyAzNjQsMzUgMjY5LDE2MCAxNzQsMjg0IDEyNyw0NjYgMTI3LDcwNSAxMjcsOTM2IDE3MywxMTE2IDI2NSwxMjQ1IDM1NiwxMzc0IDQ5NywxNDM4IDY4NiwxNDM4IDc1NCwxNDM4IDgyMSwxNDI5IDg4OCwxNDEyIDk1NSwxMzk1IDEwMDgsMTM3MiAxMDQ5LDEzNDMgTCA5OTUsMTIxNCBDIDkwMSwxMjY3IDc5OCwxMjk0IDY4NiwxMjk0IDU1NywxMjk0IDQ2MCwxMjQ3IDM5NywxMTUyIDMzMywxMDU3IDMwMSw5MDggMzAxLDcwNSAzMDEsMzEzIDQyOSwxMTcgNjg2LDExNyA3ODMsMTE3IDg1NiwxMzcgOTA1LDE3OCBMIDkwNSw1MjYgNjUxLDUyNiA2NTEsNjY4IDEwNjksNjY4IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IkMiIGhvcml6LWFkdi14PSI5NzEiIGQ9Ik0gMTA3MSw4OCBDIDk2MCwxMSA4MzIsLTI3IDY4NiwtMjcgNTAzLC0yNyAzNjUsMzYgMjcwLDE2MSAxNzUsMjg2IDEyNyw0NjggMTI3LDcwNSAxMjcsOTM0IDE3MywxMTE0IDI2NSwxMjQ0IDM1NywxMzczIDQ5NywxNDM4IDY4NiwxNDM4IDc1NCwxNDM4IDgyMSwxNDI5IDg4OCwxNDEyIDk1NSwxMzk1IDEwMDgsMTM3MiAxMDQ5LDEzNDMgTCA5OTUsMTIxNCBDIDkwMSwxMjY3IDc5OCwxMjk0IDY4NiwxMjk0IDU1NiwxMjk0IDQ1OSwxMjQ3IDM5NiwxMTUzIDMzMywxMDU4IDMwMSw5MDkgMzAxLDcwNSAzMDEsMzEzIDQyOSwxMTcgNjg2LDExNyA3OTUsMTE3IDkwNiwxNTAgMTAyMCwyMTcgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iQiIgaG9yaXotYWR2LXg9Ijg3OCIgZD0iTSA3ODQsNzc0IEMgODc4LDc1MiA5NTIsNzA5IDEwMDUsNjQ2IDEwNTgsNTgzIDEwODUsNTA3IDEwODUsNDIwIDEwODUsMjkyIDEwMzksMTkwIDk0NiwxMTQgODUzLDM4IDc0MSwwIDYxMCwwIEwgMjA5LDAgMjA5LDE0MTEgNjAwLDE0MTEgQyA3MzYsMTQxMSA4NDIsMTM4MSA5MTksMTMyMiA5OTYsMTI2MiAxMDM0LDExNzggMTAzNCwxMDY5IDEwMzQsMTAwMiAxMDEyLDk0MyA5NjgsODkxIDkyMyw4MzkgODYyLDgwMSA3ODQsNzc4IFogTSAzNzMsODUwIEwgNTY1LDg1MCBDIDc2Niw4NTAgODY2LDkyMiA4NjYsMTA2NSA4NjYsMTEzMCA4NDEsMTE4MSA3OTIsMTIxNiA3NDMsMTI1MSA2NzAsMTI2OCA1NzUsMTI2OCBMIDM3MywxMjY4IFogTSAzNzMsMTQzIEwgNTU5LDE0MyBDIDY2OCwxNDMgNzU0LDE2OCA4MTgsMjE3IDg4MSwyNjYgOTEzLDMzNCA5MTMsNDIwIDkxMyw1MTEgODgyLDU4MiA4MTksNjMyIDc1Niw2ODIgNjY2LDcwNyA1NDcsNzA3IEwgMzczLDcwNyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSImZ3Q7IiBob3Jpei1hZHYteD0iNzYzIiBkPSJNIDEwMTQsNjY0IEwgMjY2LDIxNSAyNjYsMzYyIDg2Niw3MTkgMjY2LDEwODUgMjY2LDEyMjkgMTAxNCw3ODQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iJmx0OyIgaG9yaXotYWR2LXg9Ijc4NiIgZD0iTSA5NzUsMjE1IEwgMjI3LDY2NCAyMjcsNzg0IDk3NSwxMjI5IDk3NSwxMDg1IDM3NSw3MTkgOTc1LDM2MiBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSI6IiBob3Jpei1hZHYteD0iMjU1IiBkPSJNIDYwNiwxMDU3IEMgNjQwLDEwNTcgNjY5LDEwNDYgNjkyLDEwMjMgNzE1LDEwMDAgNzI3LDk3MSA3MjcsOTM4IDcyNyw5MDUgNzE1LDg3NyA2OTIsODU0IDY2OSw4MzEgNjQwLDgxOSA2MDYsODE5IDU3Myw4MTkgNTQ1LDgzMSA1MjIsODU0IDQ5OSw4NzcgNDg3LDkwNSA0ODcsOTM4IDQ4Nyw5NzEgNDk5LDEwMDAgNTIyLDEwMjMgNTQ1LDEwNDYgNTczLDEwNTcgNjA2LDEwNTcgWiBNIDYwNiwyMjEgQyA2NDAsMjIxIDY2OSwyMTAgNjkyLDE4NyA3MTUsMTY0IDcyNywxMzYgNzI3LDEwMiA3MjcsNjkgNzE1LDQxIDY5MiwxOCA2NjksLTUgNjQwLC0xNiA2MDYsLTE2IDU3MywtMTYgNTQ1LC01IDUyMiwxOCA0OTksNDEgNDg3LDY5IDQ4NywxMDIgNDg3LDEzNiA0OTksMTY0IDUyMiwxODcgNTQ1LDIxMCA1NzMsMjIxIDYwNiwyMjEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iMSIgaG9yaXotYWR2LXg9Ijc4NiIgZD0iTSAxMDE4LDAgTCAyNzYsMCAyNzYsMTQzIDU3NSwxNDMgNTc1LDEyMzkgMjkzLDEwOTggMjkzLDEyNjIgNTk4LDE0MTkgNzM5LDE0MTkgNzM5LDE0MyAxMDE4LDE0MyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSIrIiBob3Jpei1hZHYteD0iOTcwIiBkPSJNIDEwODcsNjU3IEwgNjgyLDY1NyA2ODIsMjQ4IDU1MSwyNDggNTUxLDY1NyAxNDEsNjU3IDE0MSw3ODggNTUxLDc4OCA1NTEsMTE5OCA2ODIsMTE5OCA2ODIsNzg4IDEwODcsNzg4IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IikiIGhvcml6LWFkdi14PSI1NTUiIGQ9Ik0gMzMyLDE0MzYgQyA0ODEsMTMyMyA1OTUsMTE5MyA2NzIsMTA0NSA3NDksODk2IDc4OCw3MzggNzg4LDU3MSA3ODgsNDA1IDc0OSwyNDcgNjcyLDk4IDU5NCwtNTEgNDgxLC0xODIgMzMyLC0yOTUgTCAyNzAsLTIwOSBDIDM5MiwtMTAxIDQ4MCwxNCA1MzQsMTM2IDU4NywyNTcgNjE0LDQwMyA2MTQsNTczIDYxNCw3NDggNTg2LDg5NSA1MzEsMTAxMyA0NzYsMTEzMSAzODksMTI0MyAyNzAsMTM1MCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSIoIiBob3Jpei1hZHYteD0iNTMyIiBkPSJNIDk1OCwtMjA5IEwgODk3LC0yOTUgQyA3NDgsLTE4MiA2MzUsLTUxIDU1Nyw5OCA0NzksMjQ3IDQ0MCw0MDUgNDQwLDU3MSA0NDAsNzM4IDQ3OSw4OTYgNTU2LDEwNDUgNjMzLDExOTMgNzQ3LDEzMjMgODk3LDE0MzYgTCA5NTgsMTM1MCBDIDgzNiwxMjQxIDc0OCwxMTI2IDY5NSwxMDA3IDY0MSw4ODcgNjE0LDc0MiA2MTQsNTczIDYxNCwzOTggNjQyLDI1MCA2OTgsMTMxIDc1MywxMSA4NDAsLTEwMiA5NTgsLTIwOSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSIgIiBob3Jpei1hZHYteD0iMTIyNCIvPgogIDwvZm9udD4KIDwvZGVmcz4KIDxkZWZzPgogIDxmb250IGlkPSJFbWJlZGRlZEZvbnRfMiIgaG9yaXotYWR2LXg9IjIwNDgiPgogICA8Zm9udC1mYWNlIGZvbnQtZmFtaWx5PSJBcmlhbCBlbWJlZGRlZCIgdW5pdHMtcGVyLWVtPSIyMDQ4IiBmb250LXdlaWdodD0ibm9ybWFsIiBmb250LXN0eWxlPSJub3JtYWwiIGFzY2VudD0iMTg3MCIgZGVzY2VudD0iNDM5Ii8+CiAgIDxtaXNzaW5nLWdseXBoIGhvcml6LWFkdi14PSIyMDQ4IiBkPSJNIDAsMCBMIDIwNDcsMCAyMDQ3LDIwNDcgMCwyMDQ3IDAsMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSLigK8iIGhvcml6LWFkdi14PSIxIi8+CiAgIDxnbHlwaCB1bmljb2RlPSLCuyIgaG9yaXotYWR2LXg9Ijg3OCIgZD0iTSA3OTEsNTI4IEwgNTMwLDk4NCA2NzgsOTg0IDk5Nyw1MjggNjc4LDcyIDUzMSw3MiBaIE0gNDA0LDUyOCBMIDE0MCw5ODQgMjkxLDk4NCA2MDUsNTI4IDI5MSw3MiAxNDAsNzIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iwqsiIGhvcml6LWFkdi14PSI4NzkiIGQ9Ik0gMzQwLDUyOCBMIDU5OSw3MiA0NTMsNzIgMTM0LDUyOCA0NTMsOTg0IDYwMSw5ODQgWiBNIDcyNyw1MjggTCA5OTEsNzIgODM5LDcyIDUyNiw1MjggODM5LDk4NCA5OTEsOTg0IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InQiIGhvcml6LWFkdi14PSI1MzIiIGQ9Ik0gNTI4LDE2MSBMIDU1NCwyIEMgNTAzLC05IDQ1OCwtMTQgNDE4LC0xNCAzNTMsLTE0IDMwMiwtNCAyNjYsMTcgMjMwLDM4IDIwNSw2NSAxOTAsOTkgMTc1LDEzMiAxNjgsMjAzIDE2OCwzMTEgTCAxNjgsOTIyIDM2LDkyMiAzNiwxMDYyIDE2OCwxMDYyIDE2OCwxMzI1IDM0NywxNDMzIDM0NywxMDYyIDUyOCwxMDYyIDUyOCw5MjIgMzQ3LDkyMiAzNDcsMzAxIEMgMzQ3LDI1MCAzNTAsMjE3IDM1NywyMDIgMzYzLDE4NyAzNzMsMTc2IDM4OCwxNjcgNDAyLDE1OCA0MjIsMTU0IDQ0OSwxNTQgNDY5LDE1NCA0OTUsMTU2IDUyOCwxNjEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iciIgaG9yaXotYWR2LXg9IjYwMiIgZD0iTSAxMzMsMCBMIDEzMywxMDYyIDI5NSwxMDYyIDI5NSw5MDEgQyAzMzYsOTc2IDM3NSwxMDI2IDQxMCwxMDUwIDQ0NSwxMDc0IDQ4MywxMDg2IDUyNSwxMDg2IDU4NiwxMDg2IDY0NywxMDY3IDcxMCwxMDI4IEwgNjQ4LDg2MSBDIDYwNCw4ODcgNTYwLDkwMCA1MTYsOTAwIDQ3Nyw5MDAgNDQxLDg4OCA0MTAsODY1IDM3OSw4NDEgMzU2LDgwOCAzNDMsNzY2IDMyMyw3MDIgMzEzLDYzMiAzMTMsNTU2IEwgMzEzLDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibiIgaG9yaXotYWR2LXg9IjkwMiIgZD0iTSAxMzUsMCBMIDEzNSwxMDYyIDI5NywxMDYyIDI5Nyw5MTEgQyAzNzUsMTAyOCA0ODgsMTA4NiA2MzUsMTA4NiA2OTksMTA4NiA3NTgsMTA3NSA4MTIsMTA1MiA4NjUsMTAyOSA5MDUsOTk4IDkzMiw5NjEgOTU5LDkyNCA5NzcsODc5IDk4OCw4MjggOTk1LDc5NSA5OTgsNzM2IDk5OCw2NTMgTCA5OTgsMCA4MTgsMCA4MTgsNjQ2IEMgODE4LDcxOSA4MTEsNzc0IDc5Nyw4MTEgNzgzLDg0NyA3NTgsODc2IDcyMyw4OTggNjg3LDkxOSA2NDUsOTMwIDU5Nyw5MzAgNTIwLDkzMCA0NTQsOTA2IDM5OSw4NTcgMzQzLDgwOCAzMTUsNzE2IDMxNSw1ODAgTCAzMTUsMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJpIiBob3Jpei1hZHYteD0iMjA5IiBkPSJNIDEzNiwxMjU5IEwgMTM2LDE0NjYgMzE2LDE0NjYgMzE2LDEyNTkgWiBNIDEzNiwwIEwgMTM2LDEwNjIgMzE2LDEwNjIgMzE2LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZiIgaG9yaXotYWR2LXg9IjY0OCIgZD0iTSAxNzgsMCBMIDE3OCw5MjIgMTksOTIyIDE5LDEwNjIgMTc4LDEwNjIgMTc4LDExNzUgQyAxNzgsMTI0NiAxODQsMTI5OSAxOTcsMTMzNCAyMTQsMTM4MSAyNDUsMTQxOSAyODksMTQ0OCAzMzIsMTQ3NyAzOTMsMTQ5MSA0NzIsMTQ5MSA1MjMsMTQ5MSA1NzksMTQ4NSA2NDAsMTQ3MyBMIDYxMywxMzE2IEMgNTc2LDEzMjMgNTQwLDEzMjYgNTA3LDEzMjYgNDUyLDEzMjYgNDE0LDEzMTQgMzkxLDEyOTEgMzY4LDEyNjggMzU3LDEyMjQgMzU3LDExNjAgTCAzNTcsMTA2MiA1NjQsMTA2MiA1NjQsOTIyIDM1Nyw5MjIgMzU3LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZSIgaG9yaXotYWR2LXg9Ijk5NCIgZD0iTSA4NjIsMzQyIEwgMTA0OCwzMTkgQyAxMDE5LDIxMCA5NjQsMTI2IDg4NSw2NiA4MDYsNiA3MDQsLTI0IDU4MSwtMjQgNDI2LC0yNCAzMDMsMjQgMjEyLDEyMCAxMjEsMjE1IDc1LDM0OSA3NSw1MjIgNzUsNzAxIDEyMSw4MzkgMjEzLDkzOCAzMDUsMTAzNyA0MjQsMTA4NiA1NzEsMTA4NiA3MTMsMTA4NiA4MjksMTAzOCA5MTksOTQxIDEwMDksODQ0IDEwNTQsNzA4IDEwNTQsNTMzIDEwNTQsNTIyIDEwNTQsNTA2IDEwNTMsNDg1IEwgMjYxLDQ4NSBDIDI2OCwzNjggMzAxLDI3OSAzNjAsMjE3IDQxOSwxNTUgNDkzLDEyNCA1ODIsMTI0IDY0OCwxMjQgNzA0LDE0MSA3NTEsMTc2IDc5OCwyMTEgODM1LDI2NiA4NjIsMzQyIFogTSAyNzEsNjMzIEwgODY0LDYzMyBDIDg1Niw3MjIgODMzLDc4OSA3OTYsODM0IDczOSw5MDMgNjY0LDkzOCA1NzMsOTM4IDQ5MCw5MzggNDIxLDkxMCAzNjUsODU1IDMwOCw4MDAgMjc3LDcyNiAyNzEsNjMzIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImMiIGhvcml6LWFkdi14PSI5NDgiIGQ9Ik0gODI4LDM4OSBMIDEwMDUsMzY2IEMgOTg2LDI0NCA5MzYsMTQ5IDg1Nyw4MCA3NzcsMTEgNjc5LC0yNCA1NjMsLTI0IDQxOCwtMjQgMzAxLDI0IDIxMywxMTkgMTI0LDIxNCA4MCwzNTAgODAsNTI3IDgwLDY0MiA5OSw3NDIgMTM3LDgyOCAxNzUsOTE0IDIzMyw5NzkgMzExLDEwMjIgMzg4LDEwNjUgNDczLDEwODYgNTY0LDEwODYgNjc5LDEwODYgNzc0LDEwNTcgODQ3LDk5OSA5MjAsOTQwIDk2Nyw4NTcgOTg4LDc1MCBMIDgxMyw3MjMgQyA3OTYsNzk0IDc2Nyw4NDggNzI1LDg4NCA2ODIsOTIwIDYzMSw5MzggNTcxLDkzOCA0ODAsOTM4IDQwNyw5MDYgMzUwLDg0MSAyOTMsNzc2IDI2NSw2NzMgMjY1LDUzMiAyNjUsMzg5IDI5MiwyODYgMzQ3LDIyMSA0MDIsMTU2IDQ3MywxMjQgNTYxLDEyNCA2MzIsMTI0IDY5MSwxNDYgNzM4LDE4OSA3ODUsMjMyIDgxNSwyOTkgODI4LDM4OSBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJhIiBob3Jpei1hZHYteD0iOTk0IiBkPSJNIDgyOCwxMzEgQyA3NjEsNzQgNjk3LDM0IDYzNiwxMSA1NzQsLTEyIDUwOCwtMjQgNDM3LC0yNCAzMjAsLTI0IDIzMSw1IDE2OCw2MiAxMDUsMTE5IDc0LDE5MSA3NCwyODAgNzQsMzMyIDg2LDM4MCAxMTAsNDIzIDEzMyw0NjYgMTY0LDUwMCAyMDMsNTI2IDI0MSw1NTIgMjg0LDU3MiAzMzIsNTg1IDM2Nyw1OTQgNDIxLDYwMyA0OTIsNjEyIDYzNyw2MjkgNzQ0LDY1MCA4MTMsNjc0IDgxNCw2OTkgODE0LDcxNCA4MTQsNzIxIDgxNCw3OTQgNzk3LDg0NiA3NjMsODc2IDcxNyw5MTcgNjQ5LDkzNyA1NTgsOTM3IDQ3Myw5MzcgNDExLDkyMiAzNzEsODkzIDMzMCw4NjMgMzAwLDgxMCAyODEsNzM1IEwgMTA1LDc1OSBDIDEyMSw4MzQgMTQ3LDg5NSAxODQsOTQyIDIyMSw5ODggMjc0LDEwMjQgMzQzLDEwNDkgNDEyLDEwNzQgNDkzLDEwODYgNTg0LDEwODYgNjc1LDEwODYgNzQ4LDEwNzUgODA1LDEwNTQgODYyLDEwMzMgOTAzLDEwMDYgOTMwLDk3NCA5NTcsOTQxIDk3NSw5MDAgOTg2LDg1MSA5OTIsODIwIDk5NSw3NjUgOTk1LDY4NSBMIDk5NSw0NDUgQyA5OTUsMjc4IDk5OSwxNzIgMTAwNywxMjggMTAxNCw4MyAxMDI5LDQxIDEwNTIsMCBMIDg2NCwwIEMgODQ1LDM3IDgzMyw4MSA4MjgsMTMxIFogTSA4MTMsNTMzIEMgNzQ4LDUwNiA2NTAsNDg0IDUxOSw0NjUgNDQ1LDQ1NCAzOTMsNDQyIDM2Miw0MjkgMzMxLDQxNiAzMDgsMzk2IDI5MSwzNzEgMjc0LDM0NSAyNjYsMzE2IDI2NiwyODUgMjY2LDIzNyAyODQsMTk3IDMyMSwxNjUgMzU3LDEzMyA0MTAsMTE3IDQ4MCwxMTcgNTQ5LDExNyA2MTEsMTMyIDY2NSwxNjMgNzE5LDE5MyA3NTksMjM0IDc4NCwyODcgODAzLDMyOCA4MTMsMzg4IDgxMyw0NjcgWiIvPgogIDwvZm9udD4KIDwvZGVmcz4KIDxkZWZzPgogIDxmb250IGlkPSJFbWJlZGRlZEZvbnRfMyIgaG9yaXotYWR2LXg9IjIwNDgiPgogICA8Zm9udC1mYWNlIGZvbnQtZmFtaWx5PSJBcmlhbCBlbWJlZGRlZCIgdW5pdHMtcGVyLWVtPSIyMDQ4IiBmb250LXdlaWdodD0iYm9sZCIgZm9udC1zdHlsZT0ibm9ybWFsIiBhc2NlbnQ9IjE4NzAiIGRlc2NlbnQ9IjQzOSIvPgogICA8bWlzc2luZy1nbHlwaCBob3Jpei1hZHYteD0iMjA0OCIgZD0iTSAwLDAgTCAyMDQ3LDAgMjA0NywyMDQ3IDAsMjA0NyAwLDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ieSIgaG9yaXotYWR2LXg9IjExMDkiIGQ9Ik0gMTQsMTA2MiBMIDMxMywxMDYyIDU2NywzMDggODE1LDEwNjIgMTEwNiwxMDYyIDczMSw0MCA2NjQsLTE0NSBDIDYzOSwtMjA3IDYxNiwtMjU0IDU5NCwtMjg3IDU3MSwtMzIwIDU0NiwtMzQ2IDUxNywtMzY3IDQ4OCwtMzg3IDQ1MiwtNDAzIDQxMCwtNDE0IDM2NywtNDI1IDMxOSwtNDMxIDI2NiwtNDMxIDIxMiwtNDMxIDE1OSwtNDI1IDEwNywtNDE0IEwgODIsLTE5NCBDIDEyNiwtMjAzIDE2NiwtMjA3IDIwMSwtMjA3IDI2NiwtMjA3IDMxNSwtMTg4IDM0NiwtMTUwIDM3NywtMTExIDQwMSwtNjIgNDE4LC0zIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9InIiIGhvcml6LWFkdi14PSI3MTciIGQ9Ik0gNDE2LDAgTCAxMzUsMCAxMzUsMTA2MiAzOTYsMTA2MiAzOTYsOTExIEMgNDQxLDk4MiA0ODEsMTAyOSA1MTcsMTA1MiA1NTIsMTA3NSA1OTMsMTA4NiA2MzgsMTA4NiA3MDIsMTA4NiA3NjQsMTA2OCA4MjMsMTAzMyBMIDczNiw3ODggQyA2ODksODE5IDY0NSw4MzQgNjA0LDgzNCA1NjUsODM0IDUzMSw4MjMgNTA0LDgwMiA0NzcsNzgwIDQ1NSw3NDEgNDQwLDY4NCA0MjQsNjI3IDQxNiw1MDkgNDE2LDMyOCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJwIiBob3Jpei1hZHYteD0iMTA0MCIgZD0iTSAxMzksMTA2MiBMIDQwMSwxMDYyIDQwMSw5MDYgQyA0MzUsOTU5IDQ4MSwxMDAzIDUzOSwxMDM2IDU5NywxMDY5IDY2MSwxMDg2IDczMiwxMDg2IDg1NSwxMDg2IDk2MCwxMDM4IDEwNDYsOTQxIDExMzIsODQ0IDExNzUsNzEwIDExNzUsNTM3IDExNzUsMzYwIDExMzIsMjIyIDEwNDUsMTI0IDk1OCwyNSA4NTMsLTI0IDczMCwtMjQgNjcxLC0yNCA2MTgsLTEyIDU3MSwxMSA1MjMsMzQgNDczLDc0IDQyMCwxMzEgTCA0MjAsLTQwNCAxMzksLTQwNCBaIE0gNDE3LDU0OSBDIDQxNyw0MzAgNDQxLDM0MiA0ODgsMjg1IDUzNSwyMjggNTkzLDE5OSA2NjEsMTk5IDcyNiwxOTkgNzgxLDIyNSA4MjQsMjc4IDg2NywzMzAgODg5LDQxNiA4ODksNTM1IDg4OSw2NDYgODY3LDcyOSA4MjIsNzgzIDc3Nyw4MzcgNzIyLDg2NCA2NTYsODY0IDU4Nyw4NjQgNTMwLDgzOCA0ODUsNzg1IDQ0MCw3MzIgNDE3LDY1MyA0MTcsNTQ5IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im8iIGhvcml6LWFkdi14PSIxMTMzIiBkPSJNIDgyLDU0NiBDIDgyLDYzOSAxMDUsNzMwIDE1MSw4MTcgMTk3LDkwNCAyNjIsOTcxIDM0NywxMDE3IDQzMSwxMDYzIDUyNSwxMDg2IDYyOSwxMDg2IDc5MCwxMDg2IDkyMSwxMDM0IDEwMjQsOTMwIDExMjcsODI1IDExNzgsNjkzIDExNzgsNTM0IDExNzgsMzczIDExMjYsMjQwIDEwMjMsMTM1IDkxOSwyOSA3ODgsLTI0IDYzMSwtMjQgNTM0LC0yNCA0NDEsLTIgMzUzLDQyIDI2NCw4NiAxOTcsMTUxIDE1MSwyMzYgMTA1LDMyMSA4Miw0MjQgODIsNTQ2IFogTSAzNzAsNTMxIEMgMzcwLDQyNiAzOTUsMzQ1IDQ0NSwyODkgNDk1LDIzMyA1NTcsMjA1IDYzMCwyMDUgNzAzLDIwNSA3NjUsMjMzIDgxNSwyODkgODY0LDM0NSA4ODksNDI2IDg4OSw1MzMgODg5LDYzNyA4NjQsNzE3IDgxNSw3NzMgNzY1LDgyOSA3MDMsODU3IDYzMCw4NTcgNTU3LDg1NyA0OTUsODI5IDQ0NSw3NzMgMzk1LDcxNyAzNzAsNjM2IDM3MCw1MzEgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibiIgaG9yaXotYWR2LXg9Ijk5MyIgZD0iTSAxMTEzLDAgTCA4MzIsMCA4MzIsNTQyIEMgODMyLDY1NyA4MjYsNzMxIDgxNCw3NjUgODAyLDc5OCA3ODMsODI0IDc1Niw4NDMgNzI5LDg2MiA2OTYsODcxIDY1OCw4NzEgNjA5LDg3MSA1NjYsODU4IDUyNyw4MzEgNDg4LDgwNCA0NjIsNzY5IDQ0OCw3MjUgNDMzLDY4MSA0MjYsNjAwIDQyNiw0ODEgTCA0MjYsMCAxNDUsMCAxNDUsMTA2MiA0MDYsMTA2MiA0MDYsOTA2IEMgNDk5LDEwMjYgNjE1LDEwODYgNzU2LDEwODYgODE4LDEwODYgODc1LDEwNzUgOTI2LDEwNTMgOTc3LDEwMzAgMTAxNiwxMDAyIDEwNDMsOTY3IDEwNjksOTMyIDEwODcsODkzIDEwOTgsODQ5IDExMDgsODA1IDExMTMsNzQyIDExMTMsNjYwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Im0iIGhvcml6LWFkdi14PSIxNTk1IiBkPSJNIDEyNiwxMDYyIEwgMzg1LDEwNjIgMzg1LDkxNyBDIDQ3OCwxMDMwIDU4OCwxMDg2IDcxNiwxMDg2IDc4NCwxMDg2IDg0MywxMDcyIDg5MywxMDQ0IDk0MywxMDE2IDk4NCw5NzQgMTAxNiw5MTcgMTA2Myw5NzQgMTExMywxMDE2IDExNjcsMTA0NCAxMjIxLDEwNzIgMTI3OSwxMDg2IDEzNDAsMTA4NiAxNDE4LDEwODYgMTQ4NCwxMDcwIDE1MzgsMTAzOSAxNTkyLDEwMDcgMTYzMiw5NjAgMTY1OSw4OTkgMTY3OCw4NTQgMTY4OCw3ODAgMTY4OCw2NzkgTCAxNjg4LDAgMTQwNywwIDE0MDcsNjA3IEMgMTQwNyw3MTIgMTM5Nyw3ODAgMTM3OCw4MTEgMTM1Miw4NTEgMTMxMiw4NzEgMTI1OCw4NzEgMTIxOSw4NzEgMTE4Miw4NTkgMTE0Nyw4MzUgMTExMiw4MTEgMTA4Nyw3NzYgMTA3Miw3MzAgMTA1Nyw2ODMgMTA0OSw2MTAgMTA0OSw1MTAgTCAxMDQ5LDAgNzY4LDAgNzY4LDU4MiBDIDc2OCw2ODUgNzYzLDc1MiA3NTMsNzgyIDc0Myw4MTIgNzI4LDgzNCA3MDcsODQ5IDY4Niw4NjQgNjU3LDg3MSA2MjEsODcxIDU3OCw4NzEgNTM5LDg1OSA1MDQsODM2IDQ2OSw4MTMgNDQ1LDc3OSA0MzAsNzM1IDQxNSw2OTEgNDA3LDYxOCA0MDcsNTE2IEwgNDA3LDAgMTI2LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0ibCIgaG9yaXotYWR2LXg9IjMwMSIgZD0iTSAxNDcsMCBMIDE0NywxNDY2IDQyOCwxNDY2IDQyOCwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImkiIGhvcml6LWFkdi14PSIzMDEiIGQ9Ik0gMTQ3LDEyMDYgTCAxNDcsMTQ2NiA0MjgsMTQ2NiA0MjgsMTIwNiBaIE0gMTQ3LDAgTCAxNDcsMTA2MiA0MjgsMTA2MiA0MjgsMCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJlIiBob3Jpei1hZHYteD0iMTA0MCIgZD0iTSA3NjIsMzM4IEwgMTA0MiwyOTEgQyAxMDA2LDE4OCA5NDksMTEwIDg3Miw1NyA3OTQsMyA2OTcsLTI0IDU4MCwtMjQgMzk1LC0yNCAyNTksMzYgMTcwLDE1NyAxMDAsMjU0IDY1LDM3NiA2NSw1MjMgNjUsNjk5IDExMSw4MzcgMjAzLDkzNyAyOTUsMTAzNiA0MTEsMTA4NiA1NTIsMTA4NiA3MTAsMTA4NiA4MzUsMTAzNCA5MjYsOTMwIDEwMTcsODI1IDEwNjEsNjY1IDEwNTcsNDUwIEwgMzUzLDQ1MCBDIDM1NSwzNjcgMzc4LDMwMiA0MjEsMjU2IDQ2NCwyMDkgNTE4LDE4NiA1ODMsMTg2IDYyNywxODYgNjY0LDE5OCA2OTQsMjIyIDcyNCwyNDYgNzQ3LDI4NSA3NjIsMzM4IFogTSA3NzgsNjIyIEMgNzc2LDcwMyA3NTUsNzY1IDcxNSw4MDggNjc1LDg1MCA2MjYsODcxIDU2OSw4NzEgNTA4LDg3MSA0NTcsODQ5IDQxNyw4MDQgMzc3LDc1OSAzNTcsNjk5IDM1OCw2MjIgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iZCIgaG9yaXotYWR2LXg9IjEwNjMiIGQ9Ik0gMTEyMSwwIEwgODYwLDAgODYwLDE1NiBDIDgxNyw5NSA3NjYsNTAgNzA3LDIxIDY0OCwtOSA1ODgsLTI0IDUyOCwtMjQgNDA2LC0yNCAzMDIsMjUgMjE1LDEyNCAxMjgsMjIyIDg0LDM1OSA4NCw1MzUgODQsNzE1IDEyNiw4NTIgMjExLDk0NiAyOTYsMTAzOSA0MDMsMTA4NiA1MzIsMTA4NiA2NTEsMTA4NiA3NTMsMTAzNyA4NDAsOTM4IEwgODQwLDE0NjYgMTEyMSwxNDY2IFogTSAzNzEsNTU0IEMgMzcxLDQ0MSAzODcsMzU5IDQxOCwzMDggNDYzLDIzNSA1MjcsMTk4IDYwOCwxOTggNjczLDE5OCA3MjgsMjI2IDc3MywyODEgODE4LDMzNiA4NDEsNDE4IDg0MSw1MjcgODQxLDY0OSA4MTksNzM3IDc3NSw3OTEgNzMxLDg0NCA2NzUsODcxIDYwNiw4NzEgNTM5LDg3MSA0ODQsODQ1IDQzOSw3OTIgMzk0LDczOSAzNzEsNjU5IDM3MSw1NTQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iYyIgaG9yaXotYWR2LXg9IjEwNDAiIGQ9Ik0gMTA3Myw3NDggTCA3OTYsNjk4IEMgNzg3LDc1MyA3NjYsNzk1IDczMyw4MjMgNzAwLDg1MSA2NTcsODY1IDYwNCw4NjUgNTM0LDg2NSA0NzgsODQxIDQzNyw3OTMgMzk1LDc0NCAzNzQsNjYzIDM3NCw1NTAgMzc0LDQyNCAzOTUsMzM1IDQzOCwyODMgNDgwLDIzMSA1MzcsMjA1IDYwOCwyMDUgNjYxLDIwNSA3MDUsMjIwIDczOSwyNTEgNzczLDI4MSA3OTcsMzMzIDgxMSw0MDcgTCAxMDg3LDM2MCBDIDEwNTgsMjMzIDEwMDMsMTM4IDkyMiw3MyA4NDEsOCA3MzIsLTI0IDU5NSwtMjQgNDQwLC0yNCAzMTYsMjUgMjI0LDEyMyAxMzEsMjIxIDg1LDM1NyA4NSw1MzAgODUsNzA1IDEzMSw4NDIgMjI0LDk0MCAzMTcsMTAzNyA0NDIsMTA4NiA2MDAsMTA4NiA3MjksMTA4NiA4MzIsMTA1OCA5MDksMTAwMyA5ODUsOTQ3IDEwNDAsODYyIDEwNzMsNzQ4IFoiLz4KICAgPGdseXBoIHVuaWNvZGU9ImIiIGhvcml6LWFkdi14PSIxMDY0IiBkPSJNIDEzNSwwIEwgMTM1LDE0NjYgNDE2LDE0NjYgNDE2LDkzOCBDIDUwMywxMDM3IDYwNSwxMDg2IDcyNCwxMDg2IDg1MywxMDg2IDk2MCwxMDM5IDEwNDUsOTQ2IDExMzAsODUyIDExNzIsNzE3IDExNzIsNTQyIDExNzIsMzYxIDExMjksMjIxIDEwNDMsMTIzIDk1NiwyNSA4NTEsLTI0IDcyOCwtMjQgNjY3LC0yNCA2MDgsLTkgNTQ5LDIyIDQ5MCw1MiA0MzksOTcgMzk2LDE1NiBMIDM5NiwwIFogTSA0MTQsNTU0IEMgNDE0LDQ0NCA0MzEsMzYzIDQ2NiwzMTAgNTE1LDIzNSA1NzksMTk4IDY2MCwxOTggNzIyLDE5OCA3NzUsMjI1IDgxOSwyNzggODYyLDMzMSA4ODQsNDE0IDg4NCw1MjggODg0LDY0OSA4NjIsNzM3IDgxOCw3OTEgNzc0LDg0NCA3MTgsODcxIDY0OSw4NzEgNTgyLDg3MSA1MjYsODQ1IDQ4MSw3OTMgNDM2LDc0MCA0MTQsNjYxIDQxNCw1NTQgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iYSIgaG9yaXotYWR2LXg9IjEwMTciIGQ9Ik0gMzU3LDczOCBMIDEwMiw3ODQgQyAxMzEsODg3IDE4MCw5NjMgMjUwLDEwMTIgMzIwLDEwNjEgNDI0LDEwODYgNTYyLDEwODYgNjg3LDEwODYgNzgxLDEwNzEgODQyLDEwNDIgOTAzLDEwMTIgOTQ3LDk3NCA5NzIsOTI5IDk5Nyw4ODMgMTAwOSw3OTkgMTAwOSw2NzcgTCAxMDA2LDM0OSBDIDEwMDYsMjU2IDEwMTEsMTg3IDEwMjAsMTQzIDEwMjksOTggMTA0NSw1MSAxMDcwLDAgTCA3OTIsMCBDIDc4NSwxOSA3NzYsNDYgNzY1LDgzIDc2MCwxMDAgNzU3LDExMSA3NTUsMTE2IDcwNyw2OSA2NTYsMzQgNjAxLDExIDU0NiwtMTIgNDg4LC0yNCA0MjYsLTI0IDMxNywtMjQgMjMxLDYgMTY4LDY1IDEwNSwxMjQgNzMsMTk5IDczLDI5MCA3MywzNTAgODcsNDA0IDExNiw0NTEgMTQ1LDQ5OCAxODUsNTM0IDIzNyw1NTkgMjg4LDU4NCAzNjMsNjA1IDQ2MCw2MjQgNTkxLDY0OSA2ODIsNjcyIDczMyw2OTMgTCA3MzMsNzIxIEMgNzMzLDc3NSA3MjAsODE0IDY5Myw4MzcgNjY2LDg2MCA2MTYsODcxIDU0Miw4NzEgNDkyLDg3MSA0NTMsODYxIDQyNSw4NDIgMzk3LDgyMiAzNzQsNzg3IDM1Nyw3MzggWiBNIDczMyw1MTAgQyA2OTcsNDk4IDY0MCw0ODQgNTYyLDQ2NyA0ODQsNDUwIDQzMyw0MzQgNDA5LDQxOCAzNzIsMzkyIDM1NCwzNTkgMzU0LDMxOSAzNTQsMjgwIDM2OSwyNDYgMzk4LDIxNyA0MjcsMTg4IDQ2NSwxNzQgNTEwLDE3NCA1NjEsMTc0IDYwOSwxOTEgNjU1LDIyNCA2ODksMjQ5IDcxMSwyODAgNzIyLDMxNyA3MjksMzQxIDczMywzODcgNzMzLDQ1NCBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJUIiBob3Jpei1hZHYteD0iMTIwMiIgZD0iTSA0NzksMCBMIDQ3OSwxMjE4IDQ0LDEyMTggNDQsMTQ2NiAxMjA5LDE0NjYgMTIwOSwxMjE4IDc3NSwxMjE4IDc3NSwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IlMiIGhvcml6LWFkdi14PSIxMjAyIiBkPSJNIDc0LDQ3NyBMIDM2Miw1MDUgQyAzNzksNDA4IDQxNSwzMzcgNDY4LDI5MiA1MjEsMjQ3IDU5MiwyMjQgNjgyLDIyNCA3NzcsMjI0IDg0OSwyNDQgODk4LDI4NSA5NDYsMzI1IDk3MCwzNzIgOTcwLDQyNiA5NzAsNDYxIDk2MCw0OTAgOTQwLDUxNSA5MTksNTM5IDg4NCw1NjAgODMzLDU3OCA3OTgsNTkwIDcxOSw2MTEgNTk2LDY0MiA0MzcsNjgxIDMyNiw3MzAgMjYyLDc4NyAxNzIsODY4IDEyNyw5NjYgMTI3LDEwODIgMTI3LDExNTcgMTQ4LDEyMjcgMTkxLDEyOTIgMjMzLDEzNTcgMjk0LDE0MDYgMzc0LDE0NDAgNDUzLDE0NzQgNTQ5LDE0OTEgNjYyLDE0OTEgODQ2LDE0OTEgOTg1LDE0NTEgMTA3OCwxMzcwIDExNzEsMTI4OSAxMjE5LDExODIgMTIyNCwxMDQ3IEwgOTI4LDEwMzQgQyA5MTUsMTEwOSA4ODgsMTE2NCA4NDcsMTE5NyA4MDUsMTIzMCA3NDIsMTI0NiA2NTksMTI0NiA1NzMsMTI0NiA1MDYsMTIyOCA0NTcsMTE5MyA0MjYsMTE3MCA0MTAsMTE0MCA0MTAsMTEwMiA0MTAsMTA2NyA0MjUsMTAzOCA0NTQsMTAxMyA0OTEsOTgyIDU4Miw5NDkgNzI2LDkxNSA4NzAsODgxIDk3Nyw4NDYgMTA0Niw4MTAgMTExNSw3NzMgMTE2OSw3MjQgMTIwOCw2NjEgMTI0Nyw1OTggMTI2Niw1MjAgMTI2Niw0MjcgMTI2NiwzNDMgMTI0MywyNjQgMTE5NiwxOTEgMTE0OSwxMTggMTA4Myw2MyA5OTgsMjggOTEzLC04IDgwNiwtMjYgNjc5LC0yNiA0OTQsLTI2IDM1MSwxNyAyNTIsMTAzIDE1MywxODggOTMsMzEzIDc0LDQ3NyBaIi8+CiAgIDxnbHlwaCB1bmljb2RlPSJOIiBob3Jpei1hZHYteD0iMTE3OCIgZD0iTSAxNTIsMCBMIDE1MiwxNDY2IDQ0MCwxNDY2IDEwNDAsNDg3IDEwNDAsMTQ2NiAxMzE1LDE0NjYgMTMxNSwwIDEwMTgsMCA0MjcsOTU2IDQyNywwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9Ik0iIGhvcml6LWFkdi14PSIxNDMyIiBkPSJNIDE0NSwwIEwgMTQ1LDE0NjYgNTg4LDE0NjYgODU0LDQ2NiAxMTE3LDE0NjYgMTU2MSwxNDY2IDE1NjEsMCAxMjg2LDAgMTI4NiwxMTU0IDk5NSwwIDcxMCwwIDQyMCwxMTU0IDQyMCwwIFoiLz4KICAgPGdseXBoIHVuaWNvZGU9IkwiIGhvcml6LWFkdi14PSIxMDYzIiBkPSJNIDE1NywwIEwgMTU3LDE0NTQgNDUzLDE0NTQgNDUzLDI0NyAxMTg5LDI0NyAxMTg5LDAgWiIvPgogICA8Z2x5cGggdW5pY29kZT0iRyIgaG9yaXotYWR2LXg9IjEzODciIGQ9Ik0gODMxLDUzOSBMIDgzMSw3ODYgMTQ2OSw3ODYgMTQ2OSwyMDIgQyAxNDA3LDE0MiAxMzE3LDg5IDEyMDAsNDQgMTA4MiwtMiA5NjMsLTI1IDg0MiwtMjUgNjg5LC0yNSA1NTUsNyA0NDEsNzIgMzI3LDEzNiAyNDEsMjI4IDE4NCwzNDggMTI3LDQ2NyA5OCw1OTcgOTgsNzM4IDk4LDg5MSAxMzAsMTAyNiAxOTQsMTE0NSAyNTgsMTI2NCAzNTIsMTM1NSA0NzUsMTQxOCA1NjksMTQ2NyA2ODYsMTQ5MSA4MjYsMTQ5MSAxMDA4LDE0OTEgMTE1MCwxNDUzIDEyNTMsMTM3NyAxMzU1LDEzMDAgMTQyMSwxMTk1IDE0NTAsMTA2MCBMIDExNTYsMTAwNSBDIDExMzUsMTA3NyAxMDk3LDExMzQgMTA0MCwxMTc2IDk4MywxMjE3IDkxMSwxMjM4IDgyNiwxMjM4IDY5NywxMjM4IDU5NCwxMTk3IDUxOCwxMTE1IDQ0MSwxMDMzIDQwMyw5MTEgNDAzLDc1MCA0MDMsNTc2IDQ0Miw0NDYgNTE5LDM1OSA1OTYsMjcyIDY5OCwyMjggODIzLDIyOCA4ODUsMjI4IDk0NywyNDAgMTAxMCwyNjUgMTA3MiwyODkgMTEyNSwzMTggMTE3MCwzNTMgTCAxMTcwLDUzOSBaIi8+CiAgPC9mb250PgogPC9kZWZzPgogPGRlZnMgY2xhc3M9IlRleHRTaGFwZUluZGV4Ij4KICA8ZyBvb286c2xpZGU9ImlkMSIgb29vOmlkLWxpc3Q9ImlkMyBpZDQgaWQ1IGlkNiBpZDcgaWQ4IGlkOSBpZDEwIGlkMTEgaWQxMiBpZDEzIGlkMTQgaWQxNSBpZDE2IGlkMTcgaWQxOCBpZDE5IGlkMjAgaWQyMSBpZDIyIGlkMjMgaWQyNCBpZDI1IGlkMjYiLz4KIDwvZGVmcz4KIDxkZWZzIGNsYXNzPSJFbWJlZGRlZEJ1bGxldENoYXJzIj4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtNTczNTYiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDU4MCwxMTQxIEwgMTE2Myw1NzEgNTgwLDAgLTQsNTcxIDU4MCwxMTQxIFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTU3MzU0IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSA4LDExMjggTCAxMTM3LDExMjggMTEzNywwIDgsMCA4LDExMjggWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtMTAxNDYiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDE3NCwwIEwgNjAyLDczOSAxNzQsMTQ4MSAxNDU2LDczOSAxNzQsMCBaIE0gMTM1OCw3MzkgTCAzMDksMTM0NiA2NTksNzM5IDEzNTgsNzM5IFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTEwMTMyIiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAyMDE1LDczOSBMIDEyNzYsMCA3MTcsMCAxMjYwLDU0MyAxNzQsNTQzIDE3NCw5MzYgMTI2MCw5MzYgNzE3LDE0ODEgMTI3NCwxNDgxIDIwMTUsNzM5IFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTEwMDA3IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAwLC0yIEMgLTcsMTQgLTE2LDI3IC0yNSwzNyBMIDM1Niw1NjcgQyAyNjIsODIzIDIxNSw5NTIgMjE1LDk1NCAyMTUsOTc5IDIyOCw5OTIgMjU1LDk5MiAyNjQsOTkyIDI3Niw5OTAgMjg5LDk4NyAzMTAsOTkxIDMzMSw5OTkgMzU0LDEwMTIgTCAzODEsOTk5IDQ5Miw3NDggNzcyLDEwNDkgODM2LDEwMjQgODYwLDEwNDkgQyA4ODEsMTAzOSA5MDEsMTAyNSA5MjIsMTAwNiA4ODYsOTM3IDgzNSw4NjMgNzcwLDc4NCA3NjksNzgzIDcxMCw3MTYgNTk0LDU4NCBMIDc3NCwyMjMgQyA3NzQsMTk2IDc1MywxNjggNzExLDEzOSBMIDcyNywxMTkgQyA3MTcsOTAgNjk5LDc2IDY3Miw3NiA2NDEsNzYgNTcwLDE3OCA0NTcsMzgxIEwgMTY0LC03NiBDIDE0MiwtMTEwIDExMSwtMTI3IDcyLC0xMjcgMzAsLTEyNyA5LC0xMTAgOCwtNzYgMSwtNjcgLTIsLTUyIC0yLC0zMiAtMiwtMjMgLTEsLTEzIDAsLTIgWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtMTAwMDQiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDI4NSwtMzMgQyAxODIsLTMzIDExMSwzMCA3NCwxNTYgNTIsMjI4IDQxLDMzMyA0MSw0NzEgNDEsNTQ5IDU1LDYxNiA4Miw2NzIgMTE2LDc0MyAxNjksNzc4IDI0MCw3NzggMjkzLDc3OCAzMjgsNzQ3IDM0Niw2ODQgTCAzNjksNTA4IEMgMzc3LDQ0NCAzOTcsNDExIDQyOCw0MTAgTCAxMTYzLDExMTYgQyAxMTc0LDExMjcgMTE5NiwxMTMzIDEyMjksMTEzMyAxMjcxLDExMzMgMTI5MiwxMTE4IDEyOTIsMTA4NyBMIDEyOTIsOTY1IEMgMTI5Miw5MjkgMTI4Miw5MDEgMTI2Miw4ODEgTCA0NDIsNDcgQyAzOTAsLTYgMzM4LC0zMyAyODUsLTMzIFoiLz4KICA8L2c+CiAgPGcgaWQ9ImJ1bGxldC1jaGFyLXRlbXBsYXRlLTk2NzkiIHRyYW5zZm9ybT0ic2NhbGUoMC4wMDA0ODgyODEyNSwtMC4wMDA0ODgyODEyNSkiPgogICA8cGF0aCBkPSJNIDgxMywwIEMgNjMyLDAgNDg5LDU0IDM4MywxNjEgMjc2LDI2OCAyMjMsNDExIDIyMyw1OTIgMjIzLDc3MyAyNzYsOTE2IDM4MywxMDIzIDQ4OSwxMTMwIDYzMiwxMTg0IDgxMywxMTg0IDk5MiwxMTg0IDExMzYsMTEzMCAxMjQ1LDEwMjMgMTM1Myw5MTYgMTQwNyw3NzIgMTQwNyw1OTIgMTQwNyw0MTIgMTM1MywyNjggMTI0NSwxNjEgMTEzNiw1NCA5OTIsMCA4MTMsMCBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS04MjI2IiB0cmFuc2Zvcm09InNjYWxlKDAuMDAwNDg4MjgxMjUsLTAuMDAwNDg4MjgxMjUpIj4KICAgPHBhdGggZD0iTSAzNDYsNDU3IEMgMjczLDQ1NyAyMDksNDgzIDE1NSw1MzUgMTAxLDU4NiA3NCw2NDkgNzQsNzIzIDc0LDc5NiAxMDEsODU5IDE1NSw5MTEgMjA5LDk2MyAyNzMsOTg5IDM0Niw5ODkgNDE5LDk4OSA0ODAsOTYzIDUzMSw5MTAgNTgyLDg1OSA2MDgsNzk2IDYwOCw3MjMgNjA4LDY0OCA1ODMsNTg2IDUzMiw1MzUgNDgyLDQ4MyA0MjAsNDU3IDM0Niw0NTcgWiIvPgogIDwvZz4KICA8ZyBpZD0iYnVsbGV0LWNoYXItdGVtcGxhdGUtODIxMSIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gLTQsNDU5IEwgMTEzNSw0NTkgMTEzNSw2MDYgLTQsNjA2IC00LDQ1OSBaIi8+CiAgPC9nPgogIDxnIGlkPSJidWxsZXQtY2hhci10ZW1wbGF0ZS02MTU0OCIgdHJhbnNmb3JtPSJzY2FsZSgwLjAwMDQ4ODI4MTI1LC0wLjAwMDQ4ODI4MTI1KSI+CiAgIDxwYXRoIGQ9Ik0gMTczLDc0MCBDIDE3Myw5MDMgMjMxLDEwNDMgMzQ2LDExNTkgNDYyLDEyNzQgNjAxLDEzMzIgNzY1LDEzMzIgOTI4LDEzMzIgMTA2NywxMjc0IDExODMsMTE1OSAxMjk5LDEwNDMgMTM1Nyw5MDMgMTM1Nyw3NDAgMTM1Nyw1NzcgMTI5OSw0MzcgMTE4MywzMjIgMTA2NywyMDYgOTI4LDE0OCA3NjUsMTQ4IDYwMSwxNDggNDYyLDIwNiAzNDYsMzIyIDIzMSw0MzcgMTczLDU3NyAxNzMsNzQwIFoiLz4KICA8L2c+CiA8L2RlZnM+CiA8ZGVmcyBjbGFzcz0iVGV4dEVtYmVkZGVkQml0bWFwcyIvPgogPGcgY2xhc3M9IlNsaWRlR3JvdXAiPgogIDxnPgogICA8ZyBpZD0iY29udGFpbmVyLWlkMSI+CiAgICA8ZyBpZD0iaWQxIiBjbGFzcz0iU2xpZGUiIGNsaXAtcGF0aD0idXJsKCNwcmVzZW50YXRpb25fY2xpcF9wYXRoKSI+CiAgICAgPGcgY2xhc3M9IlBhZ2UiPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjEwMDAiIHk9IjEzNTAiIHdpZHRoPSI1NjAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjU1LDI1NSwxNTMpIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMzgwMCwyMzUwIEwgMTAwMCwyMzUwIDEwMDAsMTM1MCA2NjAwLDEzNTAgNjYwMCwyMzUwIDM4MDAsMjM1MCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIyOTY2IiB5PSIxNzYzIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj7Cq+KAr2ludGVyZmFjZeKAr8K7PC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzUzcHgiIGZvbnQtd2VpZ2h0PSI3MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIyODA1IiB5PSIyMTQ5Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5OYW1lU3BhY2U8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDQiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI5ODUiIHk9IjEzMzUiIHdpZHRoPSI1NjMxIiBoZWlnaHQ9IjIzMzEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIHN0cm9rZS13aWR0aD0iMzAiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMzgwMCwzNjUwIEwgMTAwMCwzNjUwIDEwMDAsMTM1MCA2NjAwLDEzNTAgNjYwMCwzNjUwIDM4MDAsMzY1MCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNTAwIiB5PSIyOTM4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIGlzR2xvYmFsIDogQm9vbGVhbjwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTUwMCIgeT0iMzI5NCI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyBuYW1lICAgICA6IEdlbmVyaWNOYW1lPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5MaW5lU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkNSI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9Ijk5OSIgeT0iMjM0OSIgd2lkdGg9IjU2MDMiIGhlaWdodD0iMyIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSAxMDAwLDIzNTAgTCA2NjAwLDIzNTAiLz4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkNiI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjkyMDAiIHk9IjEwMDAiIHdpZHRoPSI4ODAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjU1LDI1NSwxNTMpIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMTM2MDAsMjAwMCBMIDkyMDAsMjAwMCA5MjAwLDEwMDAgMTgwMDAsMTAwMCAxODAwMCwyMDAwIDEzNjAwLDIwMDAgWiIvPgogICAgICAgIDx0ZXh0IGNsYXNzPSJUZXh0U2hhcGUiPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTI3NjYiIHk9IjE0MTMiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPsKr4oCvaW50ZXJmYWNl4oCvwrs8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzNTNweCIgZm9udC13ZWlnaHQ9IjcwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjEyNDY4IiB5PSIxNzk5Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5HZW5lcmljTmFtZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkNyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjkxODUiIHk9Ijk4NSIgd2lkdGg9Ijg4MzEiIGhlaWdodD0iMzAzMSIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgc3Ryb2tlLXdpZHRoPSIzMCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxMzYwMCw0MDAwIEwgOTIwMCw0MDAwIDkyMDAsMTAwMCAxODAwMCwxMDAwIDE4MDAwLDQwMDAgMTM2MDAsNDAwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI5NzAwIiB5PSIyNTg4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIGRlcHRoICAgICDCoCAgICAgICAgOiBJbnRlZ2VyPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSI5NzAwIiB5PSIyOTQ0Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIHBhcnNlZE5hbWUgICAgICAgICA6IFNlcXVlbmNlJmx0O0xvY2FsTmFtZSZndDs8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijk3MDAiIHk9IjMzMDAiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgZnVsbHlRdWFsaWZpZWROYW1lIDogR2VuZXJpY05hbWU8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9Ijk3MDAiIHk9IjM2NTYiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgcHVzaChHZW5lcmljTmFtZSkgIDogU2NvcGVkTmFtZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuTGluZVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDgiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI5MTk5IiB5PSIxOTk5IiB3aWR0aD0iODgwMyIgaGVpZ2h0PSIzIi8+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJyZ2IoMCwwLDApIiBkPSJNIDkyMDAsMjAwMCBMIDE4MDAwLDIwMDAiLz4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ29ubmVjdG9yU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkOSI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjY2MDAiIHk9IjIxNzAiIHdpZHRoPSIyNjAxIiBoZWlnaHQ9IjY5NCIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSA4ODEzLDI1MDAgTCA2ODgwLDI1MDAiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiIGQ9Ik0gOTIwMCwyNTAwIEwgOTAwMCwyNjAwIDg4MDAsMjUwMCA5MDAwLDI0MDAgOTIwMCwyNTAwIFogTSA5MTcwLDI1MDAgTCA5MDAwLDI0MTUgODgzMCwyNTAwIDkwMDAsMjU4NSA5MTcwLDI1MDAgWiIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSIgZD0iTSA2NjAwLDI1MDAgTCA2ODc5LDIzNTAgNjkwMCwyMzUwIDY5MDAsMjM2MSA2NjYxLDI0OTAgNjkwMCwyNDkwIDY5MDAsMjUxMCA2NjYxLDI1MTAgNjkwMCwyNjM5IDY5MDAsMjY1MCA2ODc5LDI2NTAgNjYwMCwyNTAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjcwMDAiIHk9IjIzNTciPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPitzY29wZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iNzAwMCIgeT0iMjg2MyI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+MTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTAiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxMDAwIiB5PSI1ODAwIiB3aWR0aD0iNDgwMSIgaGVpZ2h0PSIxMDAxIi8+CiAgICAgICAgPHBhdGggZmlsbD0icmdiKDI1NSwyNTUsMTUzKSIgc3Ryb2tlPSJub25lIiBkPSJNIDM0MDAsNjgwMCBMIDEwMDAsNjgwMCAxMDAwLDU4MDAgNTgwMCw1ODAwIDU4MDAsNjgwMCAzNDAwLDY4MDAgWiIvPgogICAgICAgIDx0ZXh0IGNsYXNzPSJUZXh0U2hhcGUiPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMjU2NiIgeT0iNjIxMyI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+wqvigK9pbnRlcmZhY2XigK/CuzwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjM1M3B4IiBmb250LXdlaWdodD0iNzAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMjI4NyIgeT0iNjU5OSI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+U2NvcGVkTmFtZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTEiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI5ODUiIHk9IjU3ODUiIHdpZHRoPSI0ODMxIiBoZWlnaHQ9IjMwMzEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIHN0cm9rZS13aWR0aD0iMzAiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMzQwMCw4ODAwIEwgMTAwMCw4ODAwIDEwMDAsNTgwMCA1ODAwLDU4MDAgNTgwMCw4ODAwIDM0MDAsODgwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNTAwIiB5PSI3Mzg4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIGhlYWQgOiBMb2NhbE5hbWU8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjE1MDAiIHk9Ijc3NDQiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPisgdGFpbCA6IEdlbmVyaWNOYW1lPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNTAwIiB5PSI4MTAwIj48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIHBhdGggOiBHZW5lcmljTmFtZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFuZGFsZSBNb25vIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTUwMCIgeT0iODQ1NiI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+KyB0aXAgIDogTG9jYWxOYW1lPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5MaW5lU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTIiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI5OTkiIHk9IjY3OTkiIHdpZHRoPSI0ODAzIiBoZWlnaHQ9IjMiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gMTAwMCw2ODAwIEwgNTgwMCw2ODAwIi8+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkN1c3RvbVNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDEzIj4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMTA0NTAiIHk9IjYwMDAiIHdpZHRoPSI2MzAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjU1LDI1NSwxNTMpIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMTM2MDAsNzAwMCBMIDEwNDUwLDcwMDAgMTA0NTAsNjAwMCAxNjc1MCw2MDAwIDE2NzUwLDcwMDAgMTM2MDAsNzAwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxMjc2NiIgeT0iNjQxMyI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+wqvigK9pbnRlcmZhY2XigK/CuzwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjM1M3B4IiBmb250LXdlaWdodD0iNzAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iMTI2NjMiIHk9IjY3OTkiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPkxvY2FsTmFtZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTQiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxMDQzNSIgeT0iNTk4NSIgd2lkdGg9IjYzMzEiIGhlaWdodD0iMTkzMSIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgc3Ryb2tlLXdpZHRoPSIzMCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxMzYwMCw3OTAwIEwgMTA0NTAsNzkwMCAxMDQ1MCw2MDAwIDE2NzUwLDYwMDAgMTY3NTAsNzkwMCAxMzYwMCw3OTAwIFoiLz4KICAgICAgICA8dGV4dCBjbGFzcz0iVGV4dFNoYXBlIj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBbmRhbGUgTW9ubyIgZm9udC1zaXplPSIzMThweCIgZm9udC13ZWlnaHQ9IjQwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjEwOTUwIiB5PSI3NTg4Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj4rIHRvU3RyaW5nIDogQ2hhcmFjdGVyU3RyaW5nPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PC90ZXh0PgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5MaW5lU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTUiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxMDQ0OSIgeT0iNjk5OSIgd2lkdGg9IjYzMDMiIGhlaWdodD0iMyIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSAxMDQ1MCw3MDAwIEwgMTY3NTAsNzAwMCIvPgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5Db25uZWN0b3JTaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxNiI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjEzNDUwIiB5PSI0MDAwIiB3aWR0aD0iMzAxIiBoZWlnaHQ9IjIwMDIiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gMTM2MDAsNjAwMCBMIDEzNjAwLDQyODAiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMTM2MDAsNDAwMCBMIDEzNzUwLDQzMDAgMTM0NTAsNDMwMCAxMzYwMCw0MDAwIFogTSAxMzYwMCw0MDQ1IEwgMTM0ODIsNDI4MCAxMzcxOCw0MjgwIDEzNjAwLDQwNDUgWiIvPgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5Db25uZWN0b3JTaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQxNyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjMzOTkiIHk9IjQ5OTkiIHdpZHRoPSIxMDIwMyIgaGVpZ2h0PSI4MDMiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gMzQwMCw1ODAwIEwgMzQwMCw1MDAwIDEzNjAwLDUwMDAiLz4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTgiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI2MzUwIiB5PSIxMDEwMCIgd2lkdGg9IjM3MDEiIGhlaWdodD0iMTAwMSIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigyNTUsMjU1LDE1MykiIHN0cm9rZT0ibm9uZSIgZD0iTSA4MjAwLDExMTAwIEwgNjM1MCwxMTEwMCA2MzUwLDEwMTAwIDEwMDUwLDEwMTAwIDEwMDUwLDExMTAwIDgyMDAsMTExMDAgWiIvPgogICAgICAgIDx0ZXh0IGNsYXNzPSJUZXh0U2hhcGUiPjx0c3BhbiBjbGFzcz0iVGV4dFBhcmFncmFwaCIgZm9udC1mYW1pbHk9IkFyaWFsLCBzYW5zLXNlcmlmIiBmb250LXNpemU9IjMxOHB4IiBmb250LXdlaWdodD0iNDAwIj48dHNwYW4gY2xhc3M9IlRleHRQb3NpdGlvbiIgeD0iNzM2NiIgeT0iMTA1MTMiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPsKr4oCvaW50ZXJmYWNl4oCvwrs8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzNTNweCIgZm9udC13ZWlnaHQ9IjcwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjczMjQiIHk9IjEwODk5Ij48dHNwYW4gZmlsbD0icmdiKDAsMCwwKSIgc3Ryb2tlPSJub25lIj5UeXBlTmFtZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMTkiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI2MzM1IiB5PSIxMDA4NSIgd2lkdGg9IjM3MzEiIGhlaWdodD0iMTQzMSIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgc3Ryb2tlLXdpZHRoPSIzMCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSA4MjAwLDExNTAwIEwgNjM1MCwxMTUwMCA2MzUwLDEwMTAwIDEwMDUwLDEwMTAwIDEwMDUwLDExNTAwIDgyMDAsMTE1MDAgWiIvPgogICAgICAgPC9nPgogICAgICA8L2c+CiAgICAgIDxnIGNsYXNzPSJjb20uc3VuLnN0YXIuZHJhd2luZy5MaW5lU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMjAiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSI2MzQ5IiB5PSIxMTA5OSIgd2lkdGg9IjM3MDMiIGhlaWdodD0iMyIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSA2MzUwLDExMTAwIEwgMTAwNTAsMTExMDAiLz4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMjEiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxNDMwMCIgeT0iMTAxMDAiIHdpZHRoPSIzNzAxIiBoZWlnaHQ9IjEwMDEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMjU1LDI1NSwxNTMpIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMTYxNTAsMTExMDAgTCAxNDMwMCwxMTEwMCAxNDMwMCwxMDEwMCAxODAwMCwxMDEwMCAxODAwMCwxMTEwMCAxNjE1MCwxMTEwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQXJpYWwsIHNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxNTMxNiIgeT0iMTA1MTMiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPsKr4oCvaW50ZXJmYWNl4oCvwrs8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48dHNwYW4gY2xhc3M9IlRleHRQYXJhZ3JhcGgiIGZvbnQtZmFtaWx5PSJBcmlhbCwgc2Fucy1zZXJpZiIgZm9udC1zaXplPSIzNTNweCIgZm9udC13ZWlnaHQ9IjcwMCI+PHRzcGFuIGNsYXNzPSJUZXh0UG9zaXRpb24iIHg9IjE0OTk5IiB5PSIxMDg5OSI+PHRzcGFuIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSI+TWVtYmVyTmFtZTwvdHNwYW4+PC90c3Bhbj48L3RzcGFuPjwvdGV4dD4KICAgICAgIDwvZz4KICAgICAgPC9nPgogICAgICA8ZyBjbGFzcz0iY29tLnN1bi5zdGFyLmRyYXdpbmcuQ3VzdG9tU2hhcGUiPgogICAgICAgPGcgaWQ9ImlkMjIiPgogICAgICAgIDxyZWN0IGNsYXNzPSJCb3VuZGluZ0JveCIgc3Ryb2tlPSJub25lIiBmaWxsPSJub25lIiB4PSIxNDI4NSIgeT0iMTAwODUiIHdpZHRoPSIzNzMxIiBoZWlnaHQ9IjE0MzEiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIHN0cm9rZS13aWR0aD0iMzAiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTYxNTAsMTE1MDAgTCAxNDMwMCwxMTUwMCAxNDMwMCwxMDEwMCAxODAwMCwxMDEwMCAxODAwMCwxMTUwMCAxNjE1MCwxMTUwMCBaIi8+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkxpbmVTaGFwZSI+CiAgICAgICA8ZyBpZD0iaWQyMyI+CiAgICAgICAgPHJlY3QgY2xhc3M9IkJvdW5kaW5nQm94IiBzdHJva2U9Im5vbmUiIGZpbGw9Im5vbmUiIHg9IjE0Mjk5IiB5PSIxMTA5OSIgd2lkdGg9IjM3MDMiIGhlaWdodD0iMyIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSAxNDMwMCwxMTEwMCBMIDE4MDAwLDExMTAwIi8+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkNvbm5lY3RvclNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDI0Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMTM0NTAiIHk9Ijc5MDAiIHdpZHRoPSIyNzAyIiBoZWlnaHQ9IjIyMDIiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gMTYxNTAsMTAxMDAgTCAxNjE1MCw5MDAwIDEzNjAwLDkwMDAgMTM2MDAsODE4MCIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSIgZD0iTSAxMzYwMCw3OTAwIEwgMTM3NTAsODIwMCAxMzQ1MCw4MjAwIDEzNjAwLDc5MDAgWiBNIDEzNjAwLDc5NDUgTCAxMzQ4Miw4MTgwIDEzNzE4LDgxODAgMTM2MDAsNzk0NSBaIi8+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkNvbm5lY3RvclNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDI1Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iMTAwNTAiIHk9IjEwNDM0IiB3aWR0aD0iNDI1MSIgaGVpZ2h0PSI3MzAiLz4KICAgICAgICA8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9InJnYigwLDAsMCkiIGQ9Ik0gMTM5MTMsMTA4MDAgTCAxMDMzMCwxMDgwMCIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSIgZD0iTSAxNDMwMCwxMDgwMCBMIDE0MTAwLDEwOTAwIDEzOTAwLDEwODAwIDE0MTAwLDEwNzAwIDE0MzAwLDEwODAwIFogTSAxNDI3MCwxMDgwMCBMIDE0MTAwLDEwNzE1IDEzOTMwLDEwODAwIDE0MTAwLDEwODg1IDE0MjcwLDEwODAwIFoiLz4KICAgICAgICA8cGF0aCBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiIGQ9Ik0gMTAwNTAsMTA4MDAgTCAxMDMyOSwxMDY1MCAxMDM1MCwxMDY1MCAxMDM1MCwxMDY2MSAxMDExMSwxMDc5MCAxMDM1MCwxMDc5MCAxMDM1MCwxMDgxMCAxMDExMSwxMDgxMCAxMDM1MCwxMDkzOSAxMDM1MCwxMDk1MCAxMDMyOSwxMDk1MCAxMDA1MCwxMDgwMCBaIi8+CiAgICAgICAgPHRleHQgY2xhc3M9IlRleHRTaGFwZSI+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxMDQ1MCIgeT0iMTA2NTciPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPithdHRyaWJ1dGVUeXBlPC90c3Bhbj48L3RzcGFuPjwvdHNwYW4+PHRzcGFuIGNsYXNzPSJUZXh0UGFyYWdyYXBoIiBmb250LWZhbWlseT0iQW5kYWxlIE1vbm8iIGZvbnQtc2l6ZT0iMzE4cHgiIGZvbnQtd2VpZ2h0PSI0MDAiPjx0c3BhbiBjbGFzcz0iVGV4dFBvc2l0aW9uIiB4PSIxMDQ1MCIgeT0iMTExNjMiPjx0c3BhbiBmaWxsPSJyZ2IoMCwwLDApIiBzdHJva2U9Im5vbmUiPjE8L3RzcGFuPjwvdHNwYW4+PC90c3Bhbj48L3RleHQ+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICAgPGcgY2xhc3M9ImNvbS5zdW4uc3Rhci5kcmF3aW5nLkNvbm5lY3RvclNoYXBlIj4KICAgICAgIDxnIGlkPSJpZDI2Ij4KICAgICAgICA8cmVjdCBjbGFzcz0iQm91bmRpbmdCb3giIHN0cm9rZT0ibm9uZSIgZmlsbD0ibm9uZSIgeD0iODE5OSIgeT0iNzkwMCIgd2lkdGg9IjU1NTIiIGhlaWdodD0iMjIwMiIvPgogICAgICAgIDxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0icmdiKDAsMCwwKSIgZD0iTSA4MjAwLDEwMTAwIEwgODIwMCw5MDAwIDEzNjAwLDkwMDAgMTM2MDAsODE4MCIvPgogICAgICAgIDxwYXRoIGZpbGw9InJnYigwLDAsMCkiIHN0cm9rZT0ibm9uZSIgZD0iTSAxMzYwMCw3OTAwIEwgMTM3NTAsODIwMCAxMzQ1MCw4MjAwIDEzNjAwLDc5MDAgWiBNIDEzNjAwLDc5NDUgTCAxMzQ4Miw4MTgwIDEzNzE4LDgxODAgMTM2MDAsNzk0NSBaIi8+CiAgICAgICA8L2c+CiAgICAgIDwvZz4KICAgICA8L2c+CiAgICA8L2c+CiAgIDwvZz4KICA8L2c+CiA8L2c+Cjwvc3ZnPg==" alt="names">
</div>
<div class="title">Figure 3. Generic names derived from ISO 19103</div>
</div>
<div class="paragraph">
<p>GeoAPI extends the ISO 19103 model by adding a (<em>path</em>, <em>tip</em>) pair in complement to the (<em>head</em>, <em>tail</em>) pair.
While the <em>head</em> and <em>tip</em> properties carry non-trivial information only inside <code>ScopedName</code>,
GeoAPI nevertheless makes them available from the <code>GenericName</code> interface (not shown in above diagram)
with the restriction that they shall return <code>this</code> (Java) or <code>self</code> (Python) when the name is an instance of <code>LocalName</code>.
This generalization makes common operations simpler without the need to check for the exact name interface.</p>
</div>
<div class="paragraph">
<p>The ISO 19103 <code>aName</code> property appears as <code>toString</code> in above UML diagram,
but this property should be mapped to the standard mechanism for representing an arbitrary object
as a character string in the target programming language.
In Java this is the <code>toString()</code> method;
in Python this is <code>__str__</code> or <code>__repr__</code>.
This specification uses the Java method name as it is more readable, but other languages should adapt.</p>
</div>
<div class="paragraph">
<p>The ISO 19103 <code>NameSpace</code> interface defines also mapping functions from a name to the object identified by that name.
But this functionality does not appear in the GeoAPI <code>NameSpace</code> interface;
instead we left these mappings to other frameworks (for example <em>Java Naming and Directory Interface</em>).</p>
</div>
<details>
<summary>Mapping to Java Naming and Directory Interface™ (JNDI)</summary>
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph">
<p>The mapping functions defined by ISO 19103 are not part of the <code>NameSpace</code> interface defined by GeoAPI.
Java applications which need such mapping may use the methods in the <code>javax.naming.Context</code> interface instead:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 9. Java Naming and Directory Interface equivalences</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 <code>NameSpace</code> member</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><code>org.opengis.util.NameSpace</code></th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><code>javax.naming.Context</code></th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>isGlobal</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>isGlobal()</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>acceptableClassList</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>generateID(Any*)</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>locate(LocalName)</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>lookup(Name)</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>name</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>name()</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>getNameInNamespace()</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>registerID(LocalName, Any)</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>bind​(Name, Object)</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>select(GenericName)</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>lookup(Name)</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>unregisterID(LocalName, Any)</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>unbind​(Name)</code></p></td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
</div>
</details>
</div>
<div class="sect3">
<h4 id="records"><a class="anchor" href="#records"></a>6.2.6. Record types</h4>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §7.7</em></p>
</div>
<div class="paragraph">
<p>Records define new data type as an heterogeneous aggregation of component data types.
Each data component is identified by a <code>MemberName</code> and has exactly one value per record.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 10. Record types mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java class or interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python class</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Schema</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Any</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.Object</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Type</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.Type</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>RecordSchema</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.RecordSchema</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Record</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.Record</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>RecordType</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.RecordType</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Field</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>FieldType</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<code>Record</code> and <code>RecordType</code> have some similarities with <code>Feature</code> and <code>FeatureType</code> respectively
      but without multi-occurrence, associations, operations and type inheritance.
      If we push the comparison with features further,
      record <code>Field</code> and <code>FieldType</code> are similar to feature <code>Attribute</code> and <code>AttributeType</code> respectively.
</td>
</tr>
</table>
</div>
<div class="paragraph">
<p>TODO: add UML diagram (show how <code>Field</code> is replaced by <code>Map&lt;type,value&gt;</code>).
Resolve <a href="https://github.com/opengeospatial/geoapi/issues/17" class="bare">https://github.com/opengeospatial/geoapi/issues/17</a></p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="internationalization"><a class="anchor" href="#internationalization"></a>6.3. Internationalization</h3>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §C.2</em></p>
</div>
<div class="paragraph">
<p>The <code>InternationalString</code> interface is defined by GeoAPI to handle textual sequences
which may potentially need to be translated for users of different locales.
Conceptually this act as a <code>CharacterString</code> but may, depending on the implementation,
provide access to locale specific representations of that string.
GeoAPI <code>InternationalString</code> is closely related, but not identical, to ISO 19103 <code>LanguageString</code>.
The main difference is that the later is a character string in one specific language,
while <code>InternationalString</code> can be a collection of character strings in different locales.
This is useful, for example, when an implementation is operating on a server that serves multiple languages simultaneously,
to allow sending string representations in the locale of the client rather than the locale of the server running the GeoAPI implementation.</p>
</div>
<div class="paragraph">
<p><span class="small">Note: <code>InternationalString</code> is inspired by JSR-150 (Internationalization Service for J2EE) with support for different timezones omitted.</span></p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 11. Linguistic types mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java class or interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python class</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>CharacterString</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.lang.String</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>str</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LanguageString</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.util.InternationalString</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LanguageCode</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>java.util.Locale</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<details>
<summary>Internationalization in Java</summary>
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph">
<p>The <code>org.opengis.util.InternationalString</code> interface provides a container for multiple versions of the same text,
each for a specific <code>java.util.Locale</code> — the identifier used in Java for a specific language, possibly in a named territory.</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="CodeRay highlight"><code data-lang="java">NameFactory factory =  ...                      <span class="comment">// Implementation dependent.</span>
InternationalString multiLingual = factory.createInternationalString(<span class="predefined-type">Map</span>.of(
        <span class="predefined-type">Locale</span>.ENGLISH, <span class="string"><span class="delimiter">&quot;</span><span class="content">My documents</span><span class="delimiter">&quot;</span></span>,
        <span class="predefined-type">Locale</span>.FRENCH,  <span class="string"><span class="delimiter">&quot;</span><span class="content">Mes documents</span><span class="delimiter">&quot;</span></span>));

<span class="predefined-type">System</span>.out.println(localized);         <span class="comment">// Language at implementation choice.</span>
<span class="predefined-type">System</span>.out.println(localized.toString(<span class="predefined-type">Locale</span>.FRENCH));</code></pre>
</div>
</div>
<div class="paragraph">
<p>The method to obtain factories is not specified by this standard and therefore depends on the design of the library implementation.
Also, the locale used by default depends on the choice of the implementation so the result of the call <code>toString()</code>
without parameters will depend on the implementation.</p>
</div>
</td>
</tr>
</table>
</div>
</details>
</div>
<div class="sect2">
<h3 id="uom"><a class="anchor" href="#uom"></a>6.4. Units of measurement</h3>
<div class="paragraph">
<p><em class="reference">From ISO 19103:2015 §C.4</em></p>
</div>
<div class="paragraph">
<p>Measurements and their units are represented by two base interfaces defined in ISO 19103:
<code>Measure</code> for the result from performing the act of ascertaining the value of a characteristic of some entity,
and <code>UnitOfMeasure</code> as a quantity adopted as a standard of measurement for other quantities of the same kind.
Those two base interfaces have a parallel set of subtypes:
<code>Length</code> (a <code>Measure</code> specialization for distances) is accompanied by <code>UomLength</code>
(a <code>UnitOfMeasure</code> specialization for length units),
<code>Area</code> is accompanied with <code>UomArea</code>, <em>etc.</em></p>
</div>
<div class="paragraph">
<p>Some languages have a standard library for units of measurement.
For example Java has standardized a set of quantity interfaces in the JSR-363 standard.
When such language-specific standard exists and provides equivalent functionality to ISO 19103, that external standard is used.
Otherwise new types are defined following ISO 19103 definitions of units of measurement.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 12. Units of measurement mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19103 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python class</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Measure</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Quantity</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>DirectedMeasure</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UnitOfMeasure</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;?&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Area</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Area</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomArea</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;Area&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Length</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Length</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Distance</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Length</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomLength</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;Length&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Angle</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Angle</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomAngle</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;Angle&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Scale</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Dimensionless</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomScale</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;Dimensionless&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Time</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Time</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomTime</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;Time&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Volume</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Volume</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomVolume</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;Volume&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Velocity</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.quantity.Speed</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomVelocity</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>javax.measure.Unit&lt;Speed&gt;</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>AngularVelocity</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>UomAngularVelocity</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Unimplemented</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<details>
<summary>Parameterized units in Java</summary>
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph">
<p>The ISO 19103 <code>Measure</code> type is represented by the <code>Quantity</code> interface in the Java API defined by JSR-363.
The Java standard defines various quantity subtypes in the same way than ISO 19103 does,
often with the same names (<code>Angle</code>, <code>Length</code>, <code>Area</code>, <code>Volume</code> and <code>Time</code>).
But contrarily to ISO 19103, JSR-363 does not define a parallel set of subtypes for units of measurement.
Instead, it defines only one unit type, <code>javax.measure.Unit&lt;? extends Quantity&gt;</code>, which is parametrized by the quantity type.
For example instead of defining a <code>UomLength</code> subtype,
developers use <code>Unit&lt;Length&gt;</code> to qualify the type of Unit or Measure being used.
Units of the same parametrized type can be used in unit conversions like below
(the <code>Units</code> class must be provided by a JSR-363 implementation):</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="CodeRay highlight"><code data-lang="java">Unit&lt;Length&gt; sourceUnit = Units.MILE;
Unit&lt;Length&gt; targetUnit = Units.KILOMETRE;
UnitConverter converter = source.getConverterTo(target);
<span class="type">double</span> source = <span class="float">123.2</span>;
<span class="type">double</span> target = converter.convert(source);</code></pre>
</div>
</div>
<div class="paragraph">
<p>where the initial calls define units of length and then a converter is used to obtain the equivalent length in a new unit.</p>
</div>
</td>
</tr>
</table>
</div>
</details>
</div>
<div class="sect2">
<h3 id="util-departures"><a class="anchor" href="#util-departures"></a>6.5. Departure from ISO 19103</h3>
<div class="paragraph">
<p>GeoAPI differs from ISO 19103 in the following ways:</p>
</div>
<div class="ulist">
<ul>
<li>
<p>Replace some ISO 19103 types by equivalent standard types of the target platform when they exist.</p>
</li>
<li>
<p>Introduction of a <code>ControlledVocabulary</code> type as a common parent for code lists and enumerations.</p>
</li>
<li>
<p>Use of <code>InternationalString</code> for holding a separate <code>String</code> for every locale to handle.</p>
</li>
<li>
<p>Introduction of a <code>NameFactory</code> for providing constructors to instantiate <code>GenericName</code> objects.</p>
</li>
</ul>
</div>
</div>
<div class="sect2">
<h3 id="metadata"><a class="anchor" href="#metadata"></a>6.6. Metadata packages</h3>
<div class="paragraph">
<p>The GeoAPI metadata packages use the <code>org.opengis.metadata</code> namespace
and implement the types defined in the specification from the International Organization for Standardization
ISO 19115-1:2014 – <em>Metadata part 1: fundamentals</em> along with the modifications of Technical Corrigendum 1 from 2016 (TODO: verify).
They are completed or merged with the types defined in ISO 19115-2:2009 – <em>Extensions for imagery and gridded data</em> (TODO: verify).</p>
</div>
<div class="paragraph">
<p>The metadata packages of GeoAPI provide container types for descriptive elements which may be related to data sets or components.
All of these data structures are essentially containers for strings, dates or numbers,
and the interfaces consist almost exclusively of methods which provide access to those types or a container.
The API defines no methods which manipulate or modify the data structures.</p>
</div>
<div class="sidebarblock">
<div class="content">
<div class="title">Write access to metadata in Java</div>
<div class="paragraph">
<p>The metadata packages of GeoAPI have been built primarily in support of the geodetic types
defined in the referencing packages and therefore consider primarily read access to the data structure contents.
The GeoAPI metadata interfaces provide no methods to set the values of the types.
Furthermore, because the way that wildcards for Java Generics have been used in the interfaces,
the collection instances are constrained to be read only.
Implementors are free to provide a fully mutable implementation of GeoAPI interfaces,
but users may need to cast to the implementation classes in order to modify a metadata.</p>
</div>
</div>
</div>
<div class="paragraph">
<p>The GeoAPI rules of method return values have been relaxed for the metadata packages.
Elsewhere in GeoAPI, methods which have a mandatory obligation in the specification
must return an instance of the return type and cannot return the Java <code>null</code> or Python <code>None</code> reference.
However, in the metadata package this rule is relaxed because data sets are encountered so frequently
which have not correctly followed the requirements of the specification.
In the GeoAPI metadata packages, methods for mandatory properties should return a valid instance,
but users should be prepared to receive <code>null</code> (Java), <code>None</code> (Python) or an empty collection.
This modification has been adopted to allow implementations sufficient latitude
to handle metadata records which do not correctly conform to the specification.
Nonetheless, sophisticated implementations can determine if a metadata record conforms with the specification
by <a href="#UML-introspection">inspecting the annotations at runtime</a>.</p>
</div>
<div class="sect3">
<h4 id="metadata-mapping"><a class="anchor" href="#metadata-mapping"></a>6.6.1. Package mapping</h4>
<div class="paragraph">
<p><em class="reference">From ISO 19115-1:2014 §6.5.[2…14]; ISO 19115-1 §6.6; ISO 19157</em></p>
</div>
<div class="paragraph">
<p>The mapping of ISO packages to GeoAPI packages follows a parallel naming scheme, shown in the table below.
Some minor packages (for example <em>Portrayal catalogue</em> which contains only one interface) have been aggregated into another package.
All packages are defined by ISO 19115 except
<em>Data quality</em> which is defined by ISO 19157 but considered by GeoAPI as metadata for historical reasons,
and <em>Reference system</em> which has been replaced by the ISO 19111 interfaces from the referencing package.</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 13. Metadata package mapping</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO package</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Java package</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Python module</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Metadata</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.base</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Identification</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.identification</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.identification</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Constraint</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.constraint</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.constraints</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Lineage</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.lineage</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.lineage</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Maintenance</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.maintenance</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.maintenance</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Spatial representation</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.spatial</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.representation</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Reference system</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.referencing.*</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Content</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.content</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.content</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Portrayal catalogue</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.base</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Distribution</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.distribution</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.distribution</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Metadata extension</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.extension</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Application schema</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.extension</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Service metadata</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.identification</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.identification</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Extent, Citation and Common</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.extent</code><br>
<code>org.opengis.metadata.citation</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.extent</code><br>
<code>opengis.metadata.citation</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Data quality</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>org.opengis.metadata.quality</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>opengis.metadata.quality</code></p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="metadata-reference-system"><a class="anchor" href="#metadata-reference-system"></a>6.6.2. Reference systems</h4>
<div class="paragraph">
<p>Coordinate Reference Systems (CRS) are defined in details by the ISO 19111 interfaces in GeoAPI <code>referencing</code> packages.
A Reference System may also use geographic identifiers (ISO 19112) instead of coordinates.
The common parent interface of <em>Coordinate Reference System</em> and <em>Reference System by Geographic Identifier</em> is <code>ReferenceSystem</code>.
GeoAPI inserts this interface between <code>IdentifiedObject</code> and <code>CoordinateReferenceSystem</code> in ISO 19111 interface hierarchy
(this is a <a href="#metadata-departures">departure from ISO standards</a>). Usages are shown below:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 14. Associations from a metadata object to a reference system</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Metadata interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Property name</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Property type</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Metadata</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>referenceSystemInfo</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>ReferenceSystem</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>Source</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>sourceReferenceSystem</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>ReferenceSystem</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>VerticalExtent</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>verticalCRS</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>VerticalCRS</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>GCPCollection</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>coordinateReferenceSystem</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>ReferenceSystem</code></p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>TODO: replace above table by UML diagram showing <code>IdentifiedObject</code> and <code>ReferenceSystem</code>.</p>
</div>
<div class="paragraph">
<p>The reference system identifier can be obtained by <code>ReferenceSystem.identifier</code>.
The reference system type (geographic, projected, compound, <em>etc.</em>) can be determined
using language-specific instructions, for example <code>instanceof</code> in Java.
If a metadata instance does not have the full reference system definition but only its identifier,
then the implementation can use a custom <code>ReferenceSystem</code> instance.</p>
</div>
</div>
<div class="sect3">
<h4 id="metadata-usage"><a class="anchor" href="#metadata-usage"></a>6.6.3. How to use</h4>
<div class="paragraph">
<p>The interfaces in the GeoAPI metadata packages are primarily containers of primitive types and other metadata types.
Metadata elements will be encountered in the data types from the referencing packages and the interfaces enable
users to obtain the elements of the data type.
As an example, we want to print a list of all the individuals (ignoring organizations)
for a document starting with a <code>Citation</code> element:</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="CodeRay highlight"><code data-lang="java">Citation citation = ...;    <span class="comment">// We assume this instance is already available</span>

<span class="keyword">for</span> (Responsibility rp : citation.getCitedResponsibleParties()) {
    <span class="keyword">if</span> (rp.getRole() == <span class="predefined-type">Role</span>.AUTHOR) {
        <span class="keyword">for</span> (Party party : rp.getParties()) {
            <span class="keyword">if</span> (party <span class="keyword">instanceof</span> Individual) {
                InternationalString author = rp.getName();
                <span class="predefined-type">System</span>.out.println(author);
            }
        }
    }
}</code></pre>
</div>
</div>
<div class="paragraph">
<p>The remainder of the metadata packages work in similar ways,
where client code must disaggregate an instance to obtain the elements needed.</p>
</div>
</div>
<div class="sect3">
<h4 id="metadata-departures"><a class="anchor" href="#metadata-departures"></a>6.6.4. Departures from ISO 19115</h4>
<div class="paragraph">
<p>A departure in the GeoAPI metadata packages from the published ISO 19115 standard is in the way GeoAPI metadata package
added the types and properties defined in the specification ISO 19115-2 – <em>Extensions for imagery and gridded data</em>.
The latter was forced to create a number of interfaces to hold elements which naturally could occur directly in the interfaces
defined by ISO 19115. We integrated such interfaces directly into the existing interfaces rather than adding complexity to the API
which exists by historical accident.
For example ISO 19115-2 defines a <code>MI_Band</code> interface which extends the <code>MD_Band</code> interface defined by ISO 19115-1,
with the addition of a <code>transferFunctionType</code> property (among others) for completing the <code>scaleFactor</code> and
<code>offset</code> properties defined by ISO 19115-1. GeoAPI merges those two interfaces together,
with <a href="#annotations">annotations</a> on each property for declaring the originating standard.
The metadata interfaces merged in such way are:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 15. Metadata ISO 19115-2 interfaces merged with ISO 19115-1 parent interfaces</caption>
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19115-1 parent interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19115-2 subclass merged with parent</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LI_ProcessStep</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LE_ProcessStep</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LI_Source</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LE_Source</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_Band</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MI_Band</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_CoverageDescription</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MI_CoverageDescription</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_Georectified</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MI_Georectified</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_Georeferenceable</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MI_Georeferenceable</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_ImageDescription</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MI_ImageDescription</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_Metadata</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MI_Metadata</code></p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>Another departure come from GeoAPI replacing the <code>MD_ReferenceSystem</code> interface by <code>RS_ReferenceSystem</code>.
Coordinate Reference Systems (CRS) are defined in details by the ISO 19111 interfaces in GeoAPI <code>referencing</code> packages.
But the ISO 19115 metadata standards do not reference those CRS interfaces directly (except in one case).
Instead the metadata standards reference CRS by their identifier (for example an EPSG code),
optionally accompanied by a code telling whether the CRS type is geographic, projected, temporal, a compound of the above, <em>etc</em>.
The ISO 19115 standard combines those two information in a <code>MD_ReferenceSystem</code> interface.
The following table lists the associations to referencing systems as defined by the metadata standards:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 16. Associations to a Reference Systems defined by ISO 19115</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19115 interface</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Association to <code>MD_ReferenceSystem</code></th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Association to ISO 19111</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_Metadata</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>referenceSystemInfo</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>LI_Source</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>sourceReferenceSystem</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>EX_VerticalExtent</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>verticalCRSId</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>verticalCRS</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MI_GCPCollection</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>coordinateReferenceSystem</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>In order to have a more uniform way to handle reference systems,
GeoAPI replaces (<em>identifier</em>, <em>type code</em>) tuples by associations to the actual <em>Reference System</em> objects.
GeoAPI does that by omitting the <code>MD_ReferenceSystem</code> and <code>MD_ReferenceSystemTypeCode</code> interfaces,
replacing them by associations to <code>RS_ReferenceSystem</code> instead as shown in the <a href="#metadata-reference-system">reference system</a> section.
The <code>RS_ReferenceSystem</code> interface is a common parent for <em>Referencing by Coordinate</em> (ISO 19111) and <em>Referencing by Geographic Identifier</em> (ISO 19112).
The functionalities can by mapped as below:</p>
</div>
<table class="tableblock frame-all grid-all stretch compact">
<caption class="title">Table 17. Mapping from ISO properties to GeoAPI replacements</caption>
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">ISO 19115 interface and property</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">GeoAPI replacement</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_ReferenceSystem.referenceSystemIdentifier</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>RS_ReferenceSystem.identifier</code></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><code>MD_ReferenceSystem.referenceSystemType</code></p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Type of the <code>RS_ReferenceSystem</code> instance</p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>As a side-effect of this replacement, <code>verticalCRSId</code> becomes redundant with <code>verticalCRS</code>.
Consequently the former is omitted from GeoAPI interfaces.</p>
</div>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="requirements"><a class="anchor" href="#requirements"></a>7. Requirements</h2>
<div class="sectionbody">
<div class="paragraph">
<p>This section describes requirements for ensuring source compatibility or binary compatibility
(when applicable) of libraries compliant with this specification.
Those requirements apply to the <em>libraries</em> made available for use by other developers.
The requirements usually do not apply to final products distributed to end users.
For example compliant libraries shall obey to method signatures declared in published OGC interfaces,
otherwise other developers could not base their developments on a common set of API.
However the final product is free to modify, add or remove methods as it sees fit;
if the GeoAPI of the final product is not invoked by any external application,
then changes to that API has no impact on inter-operability.</p>
</div>
<div class="sect2">
<h3 id="requirements-A"><a class="anchor" href="#requirements-A"></a>7.1. Requirement class A</h3>
<table class="tableblock frame-all grid-all" style="width: 90%;">
<colgroup>
<col style="width: 20%;">
<col style="width: 80%;">
</colgroup>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" colspan="2" style="background-color: #CACCCE;"><p class="tableblock"><strong>Requirements Class</strong> </p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" colspan="2" style="background-color: #FFFFFF;"><p class="tableblock"><a href="http://www.opengis.net/spec/ABCD/m.n/req/req-class-a" class="bare">http://www.opengis.net/spec/ABCD/m.n/req/req-class-a</a> </p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Target type</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Token</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Dependency</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><a href="http://www.example.org/req/blah" class="bare">http://www.example.org/req/blah</a></p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Dependency</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">urn:iso:ts:iso:19139:clause:6</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #CACCCE;"><p class="tableblock"><strong>Requirement 1</strong> </p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><a href="http://www.opengis.net/spec/ABCD/m.n/req/req-class-a/req-name-1" class="bare">http://www.opengis.net/spec/ABCD/m.n/req/req-class-a/req-name-1</a><br>
requirement description </p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #CACCCE;"><p class="tableblock"><strong>Requirement 2</strong> </p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><a href="http://www.opengis.net/spec/ABCD/m.n/req/req-class-a/req-name-2" class="bare">http://www.opengis.net/spec/ABCD/m.n/req/req-class-a/req-name-2</a><br>
requirement description </p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #CACCCE;"><p class="tableblock"><strong>Requirement 3</strong> </p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock"><a href="http://www.opengis.net/spec/ABCD/m.n/req/req-class-a/req-name-3" class="bare">http://www.opengis.net/spec/ABCD/m.n/req/req-class-a/req-name-3</a><br>
requirement description</p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>TODO</p>
</div>
<div class="sect3">
<h4 id="requirement-01"><a class="anchor" href="#requirement-01"></a>7.1.1. Requirement 1</h4>
<table class="tableblock frame-all grid-all" style="width: 90%;">
<colgroup>
<col style="width: 25%;">
<col style="width: 75%;">
</colgroup>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #CACCCE;"><p class="tableblock"><strong>Requirement 1</strong> </p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">/req/req-class-a/req-name-1<br>
<br></p>
<p class="tableblock">Redistributed modules in OGC namespace shall contain the exact same set of types, methods and properties
as listed in the API documentation published by OGC at the following locations: </p>
<p class="tableblock">Java: <a href="http://www.geoapi.org/4.0/javadoc" class="bare">http://www.geoapi.org/4.0/javadoc</a><br>
Python: TODO</p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>Note that this requirement does not mean that vendors must implement all types and methods,
or can not implement their own API in addition of GeoAPI.
This requirement only means that modules or packages inside the <code>org.opengis</code> or <code>opengis</code> namespaces
shall contain the exact same set of types as published at above links,
and each of those types shall contain the exact same set of properties as published.
But vendors are still free to implement only a subset of their choice
and throw exception for unimplemented types and methods.
Vendors can also add new types and methods, provided that those additions are in a namespace
different than <code>org.opengis</code> and <code>opengis</code>.
Finally, this requirement apply only to libraries redistributed for use by other developers.
Final applications are free to remove any unused types or methods if such removal is invisible to other developers.</p>
</div>
<div style="page-break-after: always;"></div>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="conformance-tests"><a class="anchor" href="#conformance-tests"></a>Annex A: Conformance Class Test Suite (Normative)</h2>
<div class="sectionbody">
<div class="sect2">
<h3 id="tests-A"><a class="anchor" href="#tests-A"></a>A.1. Conformance Class A</h3>
<div class="sect3">
<h4 id="test-01"><a class="anchor" href="#test-01"></a>A.1.1. Requirement 1</h4>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 20%;">
<col style="width: 80%;">
</colgroup>
<tbody>
<tr>
<th class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Test id:</p></th>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">/conf/conf-class-a/req-name-1</p></td>
</tr>
<tr>
<th class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Requirement:</p></th>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">/req/req-class-a/req-name-1</p></td>
</tr>
<tr>
<th class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Test purpose:</p></th>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Verify that all types and properties in OGC namespace are as published.</p></td>
</tr>
<tr>
<th class="tableblock halign-right valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Test method:</p></th>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">TODO</p></td>
</tr>
</tbody>
</table>
<div style="page-break-after: always;"></div>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="conformance-levels"><a class="anchor" href="#conformance-levels"></a>Annex B: Conformance Level (Normative)</h2>
<div class="sectionbody">
<div style="page-break-after: always;"></div>
</div>
</div>
<div class="sect1">
<h2 id="java"><a class="anchor" href="#java"></a>Annex C: Java Profile (Normative)</h2>
<div class="sectionbody">
<div style="page-break-after: always;"></div>
</div>
</div>
<div class="sect1">
<h2 id="python"><a class="anchor" href="#python"></a>Annex D: Python Profile (Normative)</h2>
<div class="sectionbody">
<div style="page-break-after: always;"></div>
</div>
</div>
<div class="sect1">
<h2 id="examples"><a class="anchor" href="#examples"></a>Annex E: Examples</h2>
<div class="sectionbody">
<div class="sect2">
<h3 id="UML-introspection"><a class="anchor" href="#UML-introspection"></a>E.1. UML at runtime</h3>
<div class="paragraph">
<p>The <a href="#annotations">annotations in API</a> are available at runtime by introspection.
This is useful, for example, when code needs to marshall data using the name defined by the ISO standard rather than the GeoAPI name.</p>
</div>
<div class="sect3">
<h4 id="UML-java"><a class="anchor" href="#UML-java"></a>E.1.1. Java example</h4>
<div class="paragraph">
<p>At runtime, the annotation of a reference to a GeoAPI interface can be obtained as follows,
taking as an example the method <code>getTitle()</code> in the <code>Citation</code> interface:</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="CodeRay highlight"><code data-lang="java"><span class="predefined-type">Class</span>&lt;?&gt;      type          = Citation.class;
<span class="predefined-type">Method</span>        method        = type.getMethod(<span class="string"><span class="delimiter">&quot;</span><span class="content">getTitle</span><span class="delimiter">&quot;</span></span>);
UML           annotation    = method.getAnnotation(UML.class);
<span class="predefined-type">String</span>        identifier    = annotation.identifier();          <span class="comment">// = &quot;title&quot;</span>
Specification specification = annotation.specification();       <span class="comment">// = ISO 19115-1</span>
Obligation    obligation    = annotation.obligation();          <span class="comment">// = mandatory</span></code></pre>
</div>
</div>
<div class="paragraph">
<p>Java provides a class instance like the <code>Citation.class</code> instance used here for every type, either interface or class, defined in the runtime.
The <code>getMethod(…)</code> call uses introspection to obtain a reference to the method from which the annotation can then be obtained.
The annotation system therefore provides access, at runtime, to the original definition of the element.</p>
</div>
<div style="page-break-after: always;"></div>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2>Annex F: Revision History</h2>
<div class="sectionbody">
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 20%;">
<col style="width: 20%;">
<col style="width: 20%;">
<col style="width: 20%;">
<col style="width: 20%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Date</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Release</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Editor</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Clauses modified</th>
<th class="tableblock halign-left valign-top" style="background-color: #FFFFFF;">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">2009-04-08</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">3.0.0-Draft</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Adrian Custer</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">All</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Initial public draft</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">2009-09-06</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">3.0.0-Draft-r1</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Martin Desruisseaux</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Annex E</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">List of departures</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">2010-02-11</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">3.0.0-Draft-r2</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Martin Desruisseaux</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">8.1.1, 10.1, annex F</p></td>
<td class="tableblock halign-left valign-top" style="background-color: #FFFFFF;"><p class="tableblock">Clarifications</p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>TODO: update references in above table:</p>
</div>
<div class="ulist">
<ul>
<li>
<p>8.1.1:   Primitive types</p>
</li>
<li>
<p>10.1:    Geometry packages - Defined types</p>
</li>
<li>
<p>annex E: list of departures</p>
</li>
<li>
<p>annex F: Comparison with legacy OGC specifications</p>
</li>
</ul>
</div>
<div style="page-break-after: always;"></div>
</div>
</div>
<div class="sect1">
<h2 id="bibliography"><a class="anchor" href="#bibliography"></a>Annex G: Bibliography</h2>
<div class="sectionbody">
<div class="admonitionblock note">
<table>
<tr>
<td class="icon">
<i class="fa icon-note" title="Note"></i>
</td>
<td class="content">
<div class="paragraph bibliography">
<p>[1] OGC: 16-019r4, OGC® Open Geospatial APIs - White Paper (2016)</p>
</div>
<div class="paragraph bibliography">
<p>[2] The Junit framework, <a href="http://junit.org/" class="bare">http://junit.org/</a></p>
</div>
<div class="paragraph bibliography">
<p>[3] The Unified Code for Units of Measure, <a href="http://unitsofmeasure.org/" class="bare">http://unitsofmeasure.org/</a></p>
</div>
</td>
</tr>
</table>
</div>
</div>
</div>
</div>
<div id="footer">
<div id="footer-text">
Last updated 2018-09-07 10:15:05 CEST
</div>
</div>
</body>
</html>
