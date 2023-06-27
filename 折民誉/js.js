var express = require('express');
var mysql = require('mysql');
var app = express();

// 创建MySQL连接池
var pool = mysql.createPool({
    host: 'localhost',
    user: 'root',
    password: '123456',
    database: 'file_search'
});

// 处理搜索请求
app.post('/search', function(req, res) {
    var keyword = req.body.keyword;
    pool.getConnection(function(err, connection) {
        if (err) {
            console.log('数据库连接失败：' + err.message);
            res.status(500).send('搜索失败，请稍后再试！');
        } else {
            var sql = "SELECT * FROM files WHERE content LIKE '%" + keyword + "%'";
            connection.query(sql, function(err, results) {
                connection.release();
                if (err) {
                    console.log('查询失败：' + err.message);
                    res.status(500).send('搜索失败，请稍后再试！');
                } else {
                    var html = '';
                    for (var i = 0; i < results.length; i++) {
                        html += '<p>文件名：' + results[i].filename + '</p>';
                        html += '<p>所在行：' + results[i].line + '</p>';
                        html += '<hr>';
                    }
                    res.send(html);
                }
            });
        }
    });
});

// 启动服务器
app.listen(3000, function() {
    console.log('服务器已启动，监听端口3000...');
});