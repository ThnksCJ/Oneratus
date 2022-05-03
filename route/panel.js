module.exports = function (app) {
    app.get('/panel', (req, res) => {
        res.render('index.ejs')
    })
}