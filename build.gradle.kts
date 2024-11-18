// build.gradle.kts (Projeto)

plugins {
    // Mantenha este bloco vazio se não houver plugins adicionais
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
