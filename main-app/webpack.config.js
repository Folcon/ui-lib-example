module.exports = {
    target: 'web',
    entry: './resources/public/js/compiled/out/main.js',
    output: {
        path: __dirname + "/out",
        filename: 'app.js'
    },
    resolve: {
        alias: {'xmlhttprequest': false},
        modules: ['./resources/public/js/compiled/out/', './node_modules']
    }
}
