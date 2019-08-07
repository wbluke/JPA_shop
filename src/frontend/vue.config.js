module.exports = {
    devServer: {
        port: 9090,
        proxy: {
            '/': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                ws: false
            }
        }
    }
}