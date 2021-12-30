const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const {series} = require('gulp');
const cleanCSS = require("gulp-clean-css");
const minify = require("gulp-minify");


//compile scss into css
function style() {
    return gulp.src('custom/custom.scss')
    .pipe(sass().on('error',sass.logError))
    .pipe(cleanCSS({debug: true},(details) => {
                                        console.log(`${details.name}: ${details.stats.originalSize}`);
                                        console.log(`${details.name}: ${details.stats.minifiedSize}`);
                                      }))
    .pipe(gulp.dest('src/main/resources/static/css'));

}

function minifyJs(){
 return gulp.src('src/main/resources/static/js/lcps.js', { allowEmpty: true })
         .pipe(minify({noSource: true}))
         .pipe(gulp.dest('src/main/resources/static/js'));

}

function copybootstrap() {
    return gulp.src('node_modules/bootstrap/dist/js/bootstrap.min.js')
    .pipe(gulp.dest('src/main/resources/static/js'));

}

function copyjquery() {
    return gulp.src('node_modules/jquery-ui/dist/jquery-ui.min.js')
    .pipe(gulp.dest('src/main/resources/static/js'));

}

function copyjqueryui() {
    return gulp.src('node_modules/jquery-ui/ui/widgets/datepicker.js')
    .pipe(gulp.dest('src/main/resources/static/js'));

}
function copyjqueryuicss() {
    return gulp.src('node_modules/jquery-ui/themes/base/datepicker.css')
    .pipe(gulp.dest('src/main/resources/static/js'));

}

function copydatatables() {
    return gulp.src('node_modules/datatables.net-dt/js/dataTables.dataTables.min.js')
    .pipe(gulp.dest('src/main/resources/static/js'));

}


function copychartjs() {
    return gulp.src('node_modules/chart.js/dist/chart.min.js')
    .pipe(gulp.dest('src/main/resources/static/js'));

}


exports.copydatatables = copydatatables;
exports.copyjqueryuicss = copyjqueryuicss;
exports.copybootstrap = copybootstrap;
exports.copychartjs = copychartjs;
exports.copyjquery = copyjquery;
exports.copyjqueryui = copyjqueryui;
exports.style = style;
exports.minifyJs = minifyJs;
exports.default = series(style,copybootstrap,copyjquery,copychartjs);