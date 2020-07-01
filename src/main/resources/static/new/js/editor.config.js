var editor = new wangEditor('#forEditor')
editor.customConfig.showLinkImg = false
editor.customConfig.pasteIgnoreImg = true
editor.customConfig.uploadImgMaxLength = 1
editor.customConfig.uploadImgMaxSize = 2 * 1024 * 1024
editor.customConfig.uploadImgServer = '/ueditor.do?action=uploadimage'
editor.customConfig.menus = ['head', 'bold', 'fontSize', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list', 'justify', 'quote', 'image', 'table', 'code', 'undo', 'redo']
editor.customConfig.uploadImgHooks = {
	customInsert: function (insertImg, result, editor) {
		insertImg(result.url)
	}
}
editor.customConfig.customAlert = function (info) {
	layer.msg(info)
}
editor.create()
