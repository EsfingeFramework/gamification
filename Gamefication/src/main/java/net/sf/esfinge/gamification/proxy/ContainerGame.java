package net.sf.esfinge.gamification.proxy;
import java.lang.reflect.Method;
import java.util.Map;

import net.sf.esfinge.gamification.annotation.GamificationProcessor;
import net.sf.esfinge.gamification.processors.AchievementProcessor;
import net.sf.esfinge.metadata.annotation.container.ContainerFor;
import net.sf.esfinge.metadata.annotation.container.ElementName;
import net.sf.esfinge.metadata.annotation.container.MethodProcessors;
import net.sf.esfinge.metadata.container.ContainerTarget;

@ContainerFor(ContainerTarget.TYPE)
public class ContainerGame{
	
	@ElementName
    private String nomeClasse;
	
	@MethodProcessors(GamificationProcessor.class)
	private Map<Method, AchievementProcessor> mapa;
	

	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}

	public Map<Method, AchievementProcessor> getMapa() {
		return mapa;
	}

	public void setMapa(Map<Method, AchievementProcessor> mapa) {
		this.mapa = mapa;
	}
	
}
